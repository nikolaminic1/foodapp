package com.example.foodapp.auth.service.customer.service;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.user.Addresses.AddressModel;

import java.security.Principal;
import java.util.List;

public interface CustomerAddressService {
    AddressModel get(Long id, Principal principal) throws Exception;
    AddressModel createOrUpdate(AddressRequest request, Principal principal) throws Exception;

    //    AddressModel create(AddressRequest request, Principal principal) throws Exception;
    //    AddressModel update(AddressRequest request, Principal principal) throws Exception;
    void delete(Long id, Principal principal) throws Exception;
}
