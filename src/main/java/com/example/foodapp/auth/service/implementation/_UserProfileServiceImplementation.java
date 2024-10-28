package com.example.foodapp.auth.service.implementation;


import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.profiles.AdminRepository;
import com.example.foodapp.auth.repo.profiles.CustomerRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class _UserProfileServiceImplementation implements _UserProfileService {
    private final AdminRepository adminRepo;
    private final BusinessOwnerRepo businessOwnerRepo;
    private final CustomerRepository customerRepo;

    @Override
    public Admin returnAdmin(User user) throws Exception {
        return adminRepo.findAdminByUser(user);
    }

    @Override
    public BusinessOwner returnBusinessOwner(User user) throws Exception {
        return businessOwnerRepo.findBusinessOwnerByUser(user).orElseThrow(() -> new Exception("Not found"));
    }

    @Override
    public Customer returnCustomer(User user) throws Exception {
        return customerRepo.findCustomerByUser(user).orElseThrow(() -> new Exception("Customer not found"));
    }
}
