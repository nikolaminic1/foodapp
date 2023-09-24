package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingAddressRepo extends JpaRepository<BillingAddress, Long> {
    Optional<BillingAddress> findBillingAddressById(Long id);

}
