package com.example.foodapp.business.repo;


import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.business.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepo extends JpaRepository<Business, Long> {
    Optional<Business> findById(Long id);
    Business findBusinessByBusinessOwner(BusinessOwner businessOwner);
}