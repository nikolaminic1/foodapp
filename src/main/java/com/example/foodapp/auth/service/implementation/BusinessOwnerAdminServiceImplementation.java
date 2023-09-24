package com.example.foodapp.auth.service.implementation;

import com.example.foodapp.auth.repo.profiles.BusinessOwnerProfileRepository;
import com.example.foodapp.auth.service.BusinessOwnerAdminService;
import com.example.foodapp.auth.user.UserProfile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BusinessOwnerAdminServiceImplementation implements BusinessOwnerAdminService {
    private final BusinessOwnerProfileRepository businessOwnerRepo;

    @Override
    public boolean doesBusinessOwnerExists(UserProfile userProfile) {
        return businessOwnerRepo.existsBusinessOwnerByUserProfile(userProfile);
    }

    @Override
    public Boolean delete(Long id) {
        businessOwnerRepo.deleteBusinessOwnerById(id);
        return true;
    }
}
