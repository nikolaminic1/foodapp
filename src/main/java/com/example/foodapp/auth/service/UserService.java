package com.example.foodapp.auth.service;

import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.repo.ActivationTokenRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.serializers.UserDetailSerializer;
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
    @Value("${host}")
    private String host;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
          }

    public String register(RegisterRequest request) throws Exception {
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

        Customer customer = new Customer();
        customer.setUser(user);
        return "OK";
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

    public String logout(Principal principal) throws Exception {
        return "OK";
    }

    public ConfirmationRequestResponse deleteMyProfile(Principal principal, LoginRequest request) throws Exception{
        throw new Exception("Error");
    }

    public String deleteProfileConfirmation (ConfirmationRequestResponse request) throws Exception {
        throw new Exception("Error");
    }

    public String resetPassword(ResetPasswordRequest request) {
        return "Password reset";
    }

    public String resetPasswordConfirm(ResetPasswordConfirmRequest request) {
        return "Password reset confirm";
    }
}
