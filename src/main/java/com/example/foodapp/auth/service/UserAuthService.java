package com.example.foodapp.auth.service;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwnerProfile;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.auth.user.UserProfiles._Profile;

public interface UserAuthService {
//    void signUp(SignUpRequest signUpRequest) throws Exception;
    Admin createAdminProfile(UserProfile userProfile) throws Exception;
    BusinessOwnerProfile createBusinessOwnerProfile(UserProfile userProfile) throws Exception;
    Customer createCustomerProfile(UserProfile userProfile) throws Exception;
    _Profile createProfile(Long profileId);
    void assignProfileToUserProfile(_Profile profile, User user) throws Exception;
}
