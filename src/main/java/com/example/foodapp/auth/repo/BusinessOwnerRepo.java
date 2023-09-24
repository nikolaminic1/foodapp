package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.auth.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOwnerRepo extends JpaRepository<BusinessOwner, Long> {
    BusinessOwner findBusinessOwnerByUserProfile(UserProfile userProfile);
    List<BusinessOwner> findAllByUserProfile(UserProfile userProfile);
    void deleteBusinessOwnersByUserProfile(UserProfile userProfile);
    void deleteBusinessOwnerById(Long id);
    boolean existsBusinessOwnerByUserProfile(UserProfile userProfile);
//    BusinessOwner findBusinessOwnerByBusiness(Business business);
}
