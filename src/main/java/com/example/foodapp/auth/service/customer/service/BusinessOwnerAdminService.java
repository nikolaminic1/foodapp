package com.example.foodapp.auth.service.customer.service;


import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;

public interface BusinessOwnerAdminService {
    BusinessOwner create(BusinessOwner owner) throws Exception;
    Boolean delete(Long id);
    boolean doesBusinessOwnerExists(User user);
}
