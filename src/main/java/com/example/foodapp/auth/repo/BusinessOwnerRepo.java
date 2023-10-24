package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOwnerRepo extends JpaRepository<BusinessOwner, Long> {
    BusinessOwner findBusinessOwnerByUser(User userProfile);
    List<BusinessOwner> findAllByUser(User user);
    void deleteBusinessOwnersByUser(User user);
    void deleteBusinessOwnerById(Long id);
    boolean existsBusinessOwnerByUser(User user);
//    BusinessOwner findBusinessOwnerByBusiness(Business business);
}
