package com.example.foodapp.auth.service;

import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.Customer;

public interface _UserProfileService {
    Admin returnAdmin(User userProfile) throws Exception;
    BusinessOwner returnBusinessOwner(User user) throws Exception;
    Customer returnCustomer(User userProfile) throws Exception;
}
