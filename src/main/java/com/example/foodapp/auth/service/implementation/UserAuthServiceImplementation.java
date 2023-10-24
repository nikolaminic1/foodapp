package com.example.foodapp.auth.service.implementation;

import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.*;
import com.example.foodapp.auth.service.UserAuthService;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.auth.user.UserProfiles._Profile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserAuthServiceImplementation implements UserAuthService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminRepository adminRepo;
    private final BusinessOwnerRepo businessOwnerRepo;
    private final CustomerRepository customerRepo;
    private final _ProfileRepository _profileRepo;

    @Override
    public _Profile createProfile(Long profileId) {
        _Profile profile = new _Profile();
        profile.setProfileId(profileId);
        return _profileRepo.save(profile);
    }

    @Override
    public void assignProfileToUserProfile(_Profile profile, User user) throws Exception {

    }

//    @Override
//    public void signUp(SignUpRequest signUpRequest) throws Exception {
//        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
//            throw new Exception("username is already taken");
//        }
//        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
//            throw new Exception("email is already taken");
//        }
//
//        String hashedPassword = bCryptPasswordEncoder.encode(signUpRequest.getPassword());
//        Set<Role> roles = new HashSet<>();
//        Optional<Role> userRole = roleRepo.findByName(ERole.ROLE_USER);
//
//        if (userRole.isEmpty()) {
//            throw new Exception("Role not found");
//        }
//
//        roles.add(userRole.get());
//        User user = new User();
//        user.setUsername(signUpRequest.getUsername());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(hashedPassword);
//        user.setRoles(roles);
//        user.setEnabled(false);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setStaff(false);
//        userProfile.setUser(user);
//
//        userRepo.save(user);
//        userProfileRepo.save(userProfile);
//
//        Long profileId = createCustomerProfile(userProfile).getId();
//        _Profile profile = createProfile(profileId);
//        UserProfile _userProfile = userProfileRepo.findByUser(user);
//        _userProfile.setProfileObject(profile);
//        profile.setRole(ERole.ROLE_CUSTOMER);
//        userProfileRepo.save(_userProfile);
//    }

    @Override
    public Admin createAdminProfile(User user) throws Exception {
        Admin adminProfile = new Admin();
        adminProfile.setUser(user);
        return adminRepo.save(adminProfile);
    }

    @Override
    public BusinessOwner createBusinessOwner(User user) throws Exception {
        BusinessOwner businessOwner = new BusinessOwner();
        businessOwner.setUser(user);
        return businessOwnerRepo.save(businessOwner);
    }

    @Override
    public Customer createCustomerProfile(User user) throws Exception {
        Customer customer = new Customer();
        customer.setUser(user);
        return customerRepo.save(customer);
    }
}
