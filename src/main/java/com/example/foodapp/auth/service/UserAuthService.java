package com.example.foodapp.auth.service;

import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.auth.user.UserProfiles._Profile;

public interface UserAuthService {
//    void signUp(SignUpRequest signUpRequest) throws Exception;
    Admin createAdminProfile(User userProfile) throws Exception;
    BusinessOwner createBusinessOwner(User user) throws Exception;
    Customer createCustomerProfile(User userProfile) throws Exception;
    _Profile createProfile(Long profileId);
    void assignProfileToUserProfile(_Profile profile, User user) throws Exception;
}
