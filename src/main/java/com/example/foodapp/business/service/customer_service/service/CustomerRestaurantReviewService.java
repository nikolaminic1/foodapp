package com.example.foodapp.business.service.customer_service.service;

import com.example.foodapp.business.model.BusinessReview;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;

import java.security.Principal;
import java.util.List;

public interface CustomerRestaurantReviewService {
    BusinessReview get(Long id) throws Exception;
    List<BusinessReview> list (Long businessId) throws Exception;
    BusinessReview create(CustomerReviewRequest request, Principal principal) throws Exception;
    BusinessReview update(Long id, CustomerReviewRequest request, Principal principal) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;
}
