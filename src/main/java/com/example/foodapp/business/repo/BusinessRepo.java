package com.example.foodapp.business.repo;


import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessRepo extends JpaRepository<Business, Long> {
    Optional<Business> findBusinessById(Long id);
//    List<Business> findAllByActive(Boolean active);
    Optional<Business> findBusinessByBusinessOwner(BusinessOwner businessOwner);
}