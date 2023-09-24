package com.example.foodapp.business.service.owner_service.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.enumeration.BusinessRequestStatus;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestRequest;
import com.example.foodapp.business.repo.BusinessRequestRepo;
import com.example.foodapp.business.service.owner_service.OwnerBusinessRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

import static com.example.foodapp.api_resources.RandomStringGenerator.createBusinessId;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerBusinessRequestServiceImplementation implements OwnerBusinessRequestService {
    private final BusinessRequestRepo businessRequestRepo;
    private final UserRepository userRepo;

    @Override
    public BusinessRequest create(BusinessRequestRequest payload, Principal principal) {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();

        boolean doesRequestExist = businessRequestRepo.existsBusinessRequestByUser(user);
        if(doesRequestExist){
            return null;
        }

        BusinessRequest businessRequest = new BusinessRequest();
        businessRequest.setRequest(payload.getRequest());
        businessRequest.setContactEmail(payload.getContactEmail());
        businessRequest.setContactPhone(payload.getContactPhone());
        businessRequest.setIsGranted(BusinessRequestStatus.IN_PROGRESS);
        businessRequest.setReviewed(false);
        businessRequest.setUser(user);

        String businessRequestId = createBusinessId(10);
        boolean doesExist = businessRequestRepo.existsBusinessRequestByBusinessRegistrationNumber(businessRequestId);

        while (doesExist){
            businessRequestId = createBusinessId(10);
            doesExist = businessRequestRepo.existsBusinessRequestByBusinessRegistrationNumber(businessRequestId);
            System.out.println(businessRequestId);
        }

        businessRequest.setBusinessRegistrationNumber(businessRequestId);
        businessRequestRepo.save(businessRequest);
        return businessRequest;
    }

    @Override
    public BusinessRequest get(Long id, Principal principal) {
        return null;
    }

    @Override
    public BusinessRequest update(Long id, Principal principal) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        businessRequestRepo.deleteBusinessRequestById(id);
        return true;
    }
}
