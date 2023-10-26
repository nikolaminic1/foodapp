package com.example.foodapp.business.service.admin_service.implementation;

import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.*;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.util.Collection;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminBusinessServiceImplementation implements AdminBusinessService {
    private final BusinessRepo businessRepo;
    private final UserRepository userRepo;
    private final BusinessOwnerRepo businessOwnerRepo;

    @Override
    public Business create(User user) {
        if(user != null){
            Business business = new Business();

            // it saves multiple times
            // TODO add roles for business users
//            Set<Role> roles = new HashSet<>();
//            Optional<Role> userRole = roleRepository.findByName(ERole.USER);
//            Optional<Role> userBusinessRole = roleRepository.findByName(ERole.BUSINESS);
//            userRole.ifPresent(roles::add);
//            userBusinessRole.ifPresent(roles::add);

//            UserProfile userProfile = user;

//            BusinessOwner businessOwner = new BusinessOwner();
//            businessOwner.setBusiness(business);
//            businessOwner.setUserProfile(userProfile);
//            businessOwner.setIsActive(false);
//            userProfile.setBusinessOwnerBoolean(true);
//            userProfile.setBusinessOwner(businessOwner);
//            userProfile.setStaff(true);
//            userProfileRepo.save(userProfile);
//            user.setRoles(roles);
            userRepo.save(user);
            businessRepo.save(business);
            return null;
        }
        return null;
    }

    @Override
    public Collection<Business> list(int page, int limit) {
        return businessRepo.findAll(of(page, limit)).toList();
    }

    @Override
    public Business get(Long id) {
        return businessRepo.findById(id).get();
    }

    @Override
    public Business update(Long id) {
        Business business = businessRepo.findById(id).get();
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        businessRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Business create(Business business) {
        return businessRepo.save(business);
    }
}
