package com.example.foodapp.auth.service;

import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwnerProfile;
import com.example.foodapp.auth.user.UserProfiles.Customer;

public interface _UserProfileService {
    Admin returnAdmin(UserProfile userProfile);
    BusinessOwnerProfile returnBusinessOwnerProfile(UserProfile userProfile);
    Customer returnCustomer(UserProfile userProfile);
}
