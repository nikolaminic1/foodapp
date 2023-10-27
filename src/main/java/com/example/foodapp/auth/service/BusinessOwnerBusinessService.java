package com.example.foodapp.auth.service;

import com.example.foodapp.auth.dto.BusinessOwnerUpdateRequest;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;

import java.security.Principal;

public interface BusinessOwnerBusinessService {
    BusinessOwner get(Principal principal) throws Exception;
    void update(Principal principal, BusinessOwnerUpdateRequest request) throws Exception;
}
