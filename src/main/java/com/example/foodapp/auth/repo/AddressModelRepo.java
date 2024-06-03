package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.AddressModel;
import com.example.foodapp.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;
import java.util.List;

public interface AddressModelRepo extends JpaRepository<AddressModel, Long> {
    List<AddressModel> getAddressModelsByUser(User user);
}
