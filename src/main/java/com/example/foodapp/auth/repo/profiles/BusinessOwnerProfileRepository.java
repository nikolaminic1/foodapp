package com.example.foodapp.auth.repo.profiles;

import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwnerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOwnerProfileRepository extends JpaRepository<BusinessOwnerProfile, Long> {
    BusinessOwnerProfile findByUserProfile(UserProfile profile);
//    BusinessOwner findBusinessOwnerByUserProfile(UserProfile userProfile);
//    List<BusinessOwner> findAllByUserProfile(UserProfile userProfile);
    void deleteBusinessOwnersByUserProfile(UserProfile userProfile);
    void deleteBusinessOwnerById(Long id);
    boolean existsBusinessOwnerByUserProfile(UserProfile userProfile);
//    BusinessOwner findBusinessOwnerByBusiness(Business business);
}
