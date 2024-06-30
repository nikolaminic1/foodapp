package com.example.foodapp.business.repo;


import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusinessRepo extends JpaRepository<Business, Long> {
    Optional<Business> findBusinessById(Long id);
    @Query("SELECT e FROM Business e WHERE e.isActive = :active")
    Page<Business> findBusinessesByActive(@Param("active") boolean active, Pageable pageable);
    Optional<Business> findBusinessByBusinessOwner(BusinessOwner businessOwner);
    Optional<Business> findBusinessByBusinessOwner_User(User user);
}