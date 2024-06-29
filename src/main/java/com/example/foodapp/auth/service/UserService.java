package com.example.foodapp.auth.service;

import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.repo.*;
import com.example.foodapp.auth.serializers.UserAdminSerializer;
import com.example.foodapp.auth.serializers.UserCustomerSerializer;
import com.example.foodapp.auth.serializers.UserDetailSerializer;
import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActivationTokenRepo activationTokenRepo;
    private final EmailService emailService;
    private final UserAuthService userAuthService;
    private final AddressRepo addressRepo;
    private final PasswordTokenRepo passwordTokenRepo;
    private final AuthenticationService authenticationService;
    private final DeleteTokenRepo deleteTokenRepo;
    private final TokenRepository tokenRepo;
    @Value("${host}")
    private String host;

    @Value("${host}")
    private String web_host;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // ^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$
    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void sendCustomEmail (EmailSendData data) throws Exception {

    }

    private String sendActivationEmail (String email) throws Exception {
        UUID uuid = UUID.nameUUIDFromBytes(email.getBytes());
        String token = generateNewToken();

        ActivationToken activationToken = new ActivationToken();
        activationToken.setActive(true);
        activationToken.setUid(uuid);
        activationToken.setToken(token);
        activationToken.setDateCreated(now());
        activationToken.setEmail(email);
        activationTokenRepo.save(activationToken);

        String activationLink = host + "/account-activation?uid=" + activationToken.getUid() + "&token=" + activationToken.getToken();
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("Activation email");
        emailDetails.setMessageBody(activationLink);

        try {
            emailService.sendEmail(emailDetails);
            return "OK";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Email error");
        }
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
          }

    public String register(RegisterRequest request) throws Exception {
        System.out.println(request.getEmail());
        if (!isEmailValid(request.getEmail())){
            throw new Exception("Invalid email");
        }

        if (!request.getPassword().equals(request.getRe_password())){
            throw new Exception("Passwords do not match");
        }

        String email = request.getEmail();
        boolean doesUserExists = userRepository.findByEmail(email).isPresent();

        if (email == null
                || request.getName() == null) {
            throw new Exception("Data should be provided.");
        }

        if (doesUserExists) {
            throw new Exception("User with provided email already exists.");
        }

        var user = User.builder()
                .firstname(request.getName())
//                .lastname(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .ERole(ERole.ADMIN)
                .build();

        /*
            HashSet<Role> roles = new HashSet<>();
            Role userRole = new Role();
            userRole.setName(ERole.USER);
            roles.add(userRole);
            user.setRoles(roles);
        */

        user.setEnabled(false);
        user.setAccountNonLocked(false);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        System.out.println(user.isEnabled());
        userRepository.save(user);

//        Address address = new Address();
//        address.setUser(user);
//        addressRepo.save(address);

        return this.sendActivationEmail(email);
    }

    public String activate(ConfirmationRequestResponse request) throws Exception {
        ActivationToken activationToken = activationTokenRepo
                .findActivationTokenByUid(request.getUid())
                .orElseThrow(() ->  new Exception("Activation key is not valid"));
        if (!activationToken.getActive()){
            throw new Exception("Already activated");
        }

        if (!Objects.equals(request.getToken(), activationToken.getToken())){
            throw new Exception("Token is not valid");
        }

        String email = activationToken.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("Invalid email"));

        user.setEnabled(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);

        activationToken.setActive(false);
        activationToken.setDateAccessed(now());
        activationTokenRepo.save(activationToken);

        // TODO send email after activation
//        sendCustomEmail();

        Customer customer = new Customer();
        customer.setUser(user);
        return "OK";
    }

    public String resendActivationEmail (String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User does not exists"));

        if (!user.isAccountNonLocked()) {
            throw new Exception("The account is locked.");
        }

        if (!user.isAccountNonExpired()) {
            throw new Exception("The account has expired.");
        }

        if (!user.isEnabled()) {
            return this.sendActivationEmail(user.getEmail());
        } else {
            throw new Exception("Serious error occurred.");
        }
    }

    public String getMyProfile(Principal principal) throws Exception{
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User does not exists"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserDetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(user);
    }

    public String getProfile(Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User does not exists"));
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(User.class, new UserCustomerSerializer.Serializer());
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//        mapper.registerModule(module);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserCustomerSerializer.Serializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(user);
    }

    public String logout(Principal principal) throws Exception {
        return "OK";
    }

    public String deleteMyProfile(Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("Invalid user"));

        List<UserDeleteToken> tokenList = deleteTokenRepo.findUserDeleteTokensByEmail(user.getEmail());

        tokenList.forEach((token) -> {
            token.setActive(false);
            token.setDateAccessed(now());
        });
        deleteTokenRepo.saveAll(tokenList);

        String emailForUUID = user.getEmail() + now();
        UUID uuid = UUID.nameUUIDFromBytes(emailForUUID.getBytes());
        String token = generateNewToken();

        UserDeleteToken deleteToken = new UserDeleteToken();
        deleteToken.setActive(true);
        deleteToken.setEmail(user.getEmail());
        deleteToken.setDateCreated(now());
        deleteToken.setUid(uuid);
        deleteToken.setToken(token);
        deleteTokenRepo.save(deleteToken);

        String confirmation_link = web_host + "/delete-me-confirmation" + "?uid=" + uuid + "&token=" + token;
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Account delete confirmation");
        emailDetails.setMessageBody(confirmation_link);

        try {
            emailService.sendEmail(emailDetails);
            return "OK";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Email error");
        }
    }

    public String deleteProfileConfirmation (UserDeleteRequest request) throws Exception {
        UserDeleteToken token = deleteTokenRepo.findUserDeleteTokenByUid(request.getUid())
                .orElseThrow(() -> new Exception("Token does not exist"));

        if (!token.getActive()) {
            throw new Exception("Token is not active");
        }

        if (!Objects.equals(token.getToken(), request.getToken())) {
            throw new Exception("Token is not valid");
        }

        User user = userRepository.findByEmail(token.getEmail())
                .orElseThrow(() -> new Exception("Invalid user"));

        LoginRequest loginRequest = new LoginRequest(
                user.getEmail(),
                request.getPassword()
        );

        try {
            AuthenticationResponse authentication = authenticationService.login(loginRequest);
            token.setActive(false);
            token.setDateAccessed(now());
            deleteTokenRepo.save(token);

            var allTokens = tokenRepo.findAllTokensByUser(user);
            allTokens.forEach((tokenA) -> {
                tokenA.setUser(null);
            });

            tokenRepo.saveAll(allTokens);
            userRepository.delete(user);

            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setSubject("Account deleted successfully");
            emailDetails.setMessageBody("Account deleted successfully");

            try {
                emailService.sendEmail(emailDetails);
                return "OK";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception("Email error.");
            }
        } catch (Exception e) {
            throw new Exception("User is not authenticated.");
        }

    }

    public String resetPassword(ResetPasswordRequest request) throws Exception {
        boolean doesUserExists = userRepository.findByEmail(request.getEmail()).isPresent();
        if (!doesUserExists) {
            throw new Exception("User with provided email does not exist");
        }

        List<PasswordResetToken> tokenList = passwordTokenRepo.findPasswordResetTokensByEmail(request.getEmail());
        tokenList.forEach((token) -> {
            token.setActive(false);
            token.setDateAccessed(now());
        });
        passwordTokenRepo.saveAll(tokenList);

        String emailForUUID = request.getEmail() + now();
        UUID uuid = UUID.nameUUIDFromBytes(emailForUUID.getBytes());
        String token = generateNewToken();

        PasswordResetToken passwordToken = new PasswordResetToken();
        passwordToken.setActive(true);
        passwordToken.setEmail(request.getEmail());
        passwordToken.setDateCreated(now());
        passwordToken.setUid(uuid);
        passwordToken.setToken(token);

        passwordTokenRepo.save(passwordToken);

        String activationLink = host + "/reset-password-confirmation?uid=" + uuid + "&token=" + token;
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(request.getEmail());
        emailDetails.setSubject("Password reset email");
        emailDetails.setMessageBody(activationLink);

        try {
            emailService.sendEmail(emailDetails);
            return "Email has been sent";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Email error");
        }

    }

    public String resetPasswordConfirm(ResetPasswordConfirmRequest request) throws Exception {
        System.out.println(request.getUid());
        var tt = passwordTokenRepo.findAll();
        System.out.println(tt);
        PasswordResetToken token = passwordTokenRepo.findPasswordResetTokenByUid(request.getUid())
                .orElseThrow(() -> new Exception("Token does not exist"));

        if (!token.getActive()) {
            throw new Exception("Token is not active");
        }

        if (!Objects.equals(token.getToken(), request.getToken())) {
            throw new Exception("Token is not valid");
        }

        if (!Objects.equals(request.getNew_password(), request.getRe_new_password())) {
            throw new Exception("Passwords do not match");
        }

        User user = userRepository.findByEmail(token.getEmail())
                .orElseThrow(() -> new Exception("Invalid user"));

        user.setPassword(passwordEncoder.encode(request.getNew_password()));
        userRepository.save(user);

        token.setActive(false);
        token.setDateAccessed(now());
        passwordTokenRepo.save(token);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Password reset was successful");
        emailDetails.setMessageBody("Password reset was successful");

        try {
            emailService.sendEmail(emailDetails);
            return "Email has been sent";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Email error");
        }
    }

    public User updateProfile (Principal principal, UserUpdatedRequest request) throws Exception {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("Invalid user."));

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        userRepository.save(user);
        return user;
    }
}
