package com.example.foodapp.business.service.owner_service.implementation;

import com.example.foodapp.auth.repo.RoleRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.BusinessOwnerProfileRepository;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.lang.Boolean.TRUE;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerBusinessServiceImplementation implements OwnerBusinessService {
    private final BusinessRepo businessRepo;
    private final UserRepository userRepo;
    private final RoleRepo roleRepository;
    private final BusinessOwnerProfileRepository businessOwnerRepo;


    @Override
    public Business get(Long id) {
        return businessRepo.findById(id).get();
    }

    @Override
    public Business update(Long id, BusinessUpdateRequest businessUpdateRequest, Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();

        if(businessRepo.findById(id).isPresent()){
            Business business = businessRepo.findById(id).get();
//            BusinessOwner businessOwner = businessOwnerRepo.findBusinessOwnerByBusiness(business);

//            if(businessOwner.getUserProfile() == user.getUserProfile()){
//                business.setDescription(businessUpdateRequest.getDesc());
//                business.setName(businessUpdateRequest.getName());
//                return businessRepo.save(business);
//            }
            return null;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        businessRepo.deleteById(id);
        return TRUE;
    }


}
