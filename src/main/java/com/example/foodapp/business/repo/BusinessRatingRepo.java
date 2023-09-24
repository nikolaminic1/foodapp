package com.example.foodapp.business.repo;


import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessRatingRepo extends JpaRepository<BusinessRating, Long> {
    Optional<BusinessRating> findById(Long id);
    List<BusinessRating> findBusinessRatingsByBusiness(Business business);
    Optional<BusinessRating> findBusinessRatingByCustomerAndBusiness(Customer customer, Business business);
}
