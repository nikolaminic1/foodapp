package com.example.foodapp.business.service.owner_service;


import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;

import java.security.Principal;

public interface OwnerBusinessService {
    Business get(Long id);
    Business update(Long id, BusinessUpdateRequest businessUpdateRequest, Principal principal);
    Boolean delete(Long id);
}
