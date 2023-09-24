package com.example.foodapp.auth.service.implementation;

import com.example.foodapp.auth.repo.profiles.UserProfileRepo;
import com.example.foodapp.auth.service.UserProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserProfileServiceImplementation implements UserProfileService {
    private final UserProfileRepo userProfileRepo;

    @Override
    public void deleteById(Long id) {
        userProfileRepo.deleteUserProfileById(id);
    }
}
