package com.example.foodapp.auth.service;


import com.example.foodapp.auth.user.User;

public interface BusinessOwnerAdminService {
    Boolean delete(Long id);
    boolean doesBusinessOwnerExists(User user);
}
