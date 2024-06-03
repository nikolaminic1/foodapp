package com.example.foodapp.auth.service.admin.service;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.user.Addresses.AddressModel;

import java.security.Principal;
import java.util.List;

public interface AdminAddressService {
    List<AddressModel> getAll(Principal principal) throws Exception;
    AddressModel update(AddressRequest request, Principal principal) throws Exception;
    void delete(Long id, Principal principal) throws Exception;
    AddressModel get(Long id, Principal principal) throws Exception;
}
