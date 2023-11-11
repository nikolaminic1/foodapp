package com.example.foodapp.business.service.owner_service.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.BusinessLocation;
import com.example.foodapp.business.model.Requests.AddressUpdateRequest;
import com.example.foodapp.business.repo.BusinessLocationRepo;
import com.example.foodapp.business.service.owner_service.BusinessLocationBusinessService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class BusinessLocationBusinessServiceImplementation implements BusinessLocationBusinessService {
    private final BusinessLocationRepo businessLocationRepo;
    private final UserRepository userRepo;

    @Override
    public BusinessLocation get(Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        return businessLocationRepo
                .findBusinessLocationByBusiness_BusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Location not found"));
    }

    @Override
    public String update(AddressUpdateRequest request, Principal principal) throws Exception {
        System.out.println(request);
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        BusinessLocation location = businessLocationRepo
                .findBusinessLocationByBusiness_BusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Location not found"));
        location.setBuildingNumber(request.getBuildingNumber());
        location.setCityName(request.getCityName());
        location.setStreetName(request.getStreetName());
        location.setZipCode(request.getZipCode());
        location.setFlatNumber(request.getFlatNumber());
        return "OK";
    }
}
