package com.example.foodapp.business.repo;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessLocationRepo extends JpaRepository<BusinessLocation, Long> {
    Optional<BusinessLocation> findBusinessLocationById(Long id);
    Optional<BusinessLocation> findBusinessLocationByBusiness_BusinessOwner_User(User user);
}
