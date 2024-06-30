package com.example.foodapp.business.service.customer_service.service;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CustomerRestaurantService {
    String get(Long id) throws Exception;
    String list (String page, String per_page) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;
}
