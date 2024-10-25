package com.example.foodapp.auth.service.admin.service;

import com.example.foodapp._api.PageableRequest;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import com.example.foodapp.auth.user.User;

import java.security.Principal;
import java.util.List;

public interface AdminUsersService {
    String list(PageableRequest request, Principal principal) throws Exception;
    String get(Long id, Principal principal) throws Exception;
    void deleteUser(Long id, Principal principal) throws Exception;
}
