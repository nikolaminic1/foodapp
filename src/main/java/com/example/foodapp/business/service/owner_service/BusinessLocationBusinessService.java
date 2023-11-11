package com.example.foodapp.business.service.owner_service;

import com.example.foodapp.business.model.BusinessLocation;
import com.example.foodapp.business.model.Requests.AddressUpdateRequest;

import java.security.Principal;

public interface BusinessLocationBusinessService {
    BusinessLocation get(Principal principal) throws Exception;
    String update(AddressUpdateRequest request, Principal principal) throws Exception;
}
