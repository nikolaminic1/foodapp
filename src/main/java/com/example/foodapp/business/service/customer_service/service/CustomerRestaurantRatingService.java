package com.example.foodapp.business.service.customer_service.service;


import com.example.foodapp.business.model.BusinessRating;
import com.example.foodapp.business.model.Requests.CustomerBusinessRatingRequest;

import java.security.Principal;
import java.util.List;

public interface CustomerRestaurantRatingService {
    BusinessRating get(Long id) throws Exception;
    List<BusinessRating> list (Long businessId) throws Exception;
    BusinessRating create(CustomerBusinessRatingRequest request, Principal principal) throws Exception;
    BusinessRating update(CustomerBusinessRatingRequest request, Principal principal) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;

}
