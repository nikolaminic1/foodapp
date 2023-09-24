package com.example.foodapp.auth.service.implementation;


import com.example.foodapp.auth.repo.profiles.AdminRepository;
import com.example.foodapp.auth.repo.profiles.BusinessOwnerProfileRepository;
import com.example.foodapp.auth.repo.profiles.CustomerRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwnerProfile;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class _UserProfileServiceImplementation implements _UserProfileService {
    private final AdminRepository adminRepo;
    private final BusinessOwnerProfileRepository businessOwnerProfileRepo;
    private final CustomerRepository customerRepo;

    @Override
    public Admin returnAdmin(UserProfile userProfile) {
        return adminRepo.findByUserProfile(userProfile);
    }

    @Override
    public BusinessOwnerProfile returnBusinessOwnerProfile(UserProfile userProfile) {
        return businessOwnerProfileRepo.findByUserProfile(userProfile);
    }

    @Override
    public Customer returnCustomer(UserProfile userProfile) {
        return customerRepo.findByUserProfile(userProfile);
    }
}
