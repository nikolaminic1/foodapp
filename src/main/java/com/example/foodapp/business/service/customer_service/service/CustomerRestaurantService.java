package com.example.foodapp.business.service.customer_service.service;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CustomerRestaurantService {
    Business get(Long id) throws Exception;
    List<Business> list () throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;
}
