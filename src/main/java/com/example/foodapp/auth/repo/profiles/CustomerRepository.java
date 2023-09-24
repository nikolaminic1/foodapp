package com.example.foodapp.auth.repo.profiles;

import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    Customer findByUserProfile(UserProfile userProfile);
}
