package com.example.foodapp.auth.service;


import com.example.foodapp.auth.user.UserProfile;

public interface BusinessOwnerAdminService {
    Boolean delete(Long id);
    boolean doesBusinessOwnerExists(UserProfile userProfile);
}
