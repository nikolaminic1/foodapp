package com.example.foodapp.business.service.admin_service;


import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;

import java.security.Principal;
import java.util.Collection;

public interface AdminBusinessService {
    Business create(User user);
    Business create(Business business);
    Collection<Business> list(int page, int limit);
    Business get(Long id);
    Business update(Business business);
    Boolean delete(Long id);
}
