package com.example.foodapp.business.service.owner_service;


import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestRequest;

import java.security.Principal;

public interface OwnerBusinessRequestService {
    BusinessRequest create(BusinessRequestRequest payload, Principal principal);
    BusinessRequest get(Long id, Principal principal);
    BusinessRequest update(Long id, Principal principal);
    Boolean delete(Long id, Principal principal);
}
