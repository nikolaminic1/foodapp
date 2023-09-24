package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Long> {
    Optional<ShippingAddress> findShippingAddressById(Long id);
}
