package com.example.foodapp.business.repo;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<BusinessReview, Long> {
    List<BusinessReview> findBusinessReviewsByBusiness(Business business);
}
