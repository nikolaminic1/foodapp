package com.example.foodapp.auth.service.implementation;

import com.example.foodapp.auth.dto.BusinessOwnerUpdateRequest;
import com.example.foodapp.auth.service.BusinessOwnerBusinessService;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BusinessOwnerBusinessServiceImplementation implements BusinessOwnerBusinessService {
    @Override
    public BusinessOwner get(Principal principal) throws Exception {
        return null;
    }

    @Override
    public void update(Principal principal, BusinessOwnerUpdateRequest request) throws Exception {

    }
}
