package com.example.foodapp.auth.service.customer.implementation;

import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.service.customer.service.BusinessOwnerAdminService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BusinessOwnerAdminServiceImplementation implements BusinessOwnerAdminService {
    private final BusinessOwnerRepo businessOwnerRepo;

    @Override
    public boolean doesBusinessOwnerExists(User user) {
        return businessOwnerRepo.existsBusinessOwnerByUser(user);
    }

    @Override
    public Boolean delete(Long id) {
        businessOwnerRepo.deleteBusinessOwnerById(id);
        return true;
    }

    @Override
    public BusinessOwner create(BusinessOwner owner) throws Exception {
        return businessOwnerRepo.save(owner);
    }
}
