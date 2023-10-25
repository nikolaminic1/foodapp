package com.example.foodapp.business.service.customer_service.implementation;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerRestaurantServiceImplementation implements CustomerRestaurantService {
    private final BusinessRepo businessRepo;

    @Override
    public Business get(Long id) throws Exception {
        return businessRepo.findBusinessById(id).orElseThrow(() -> new Exception("The restaurant does not exist"));
    }

    @Override
    public List<Business> list() throws Exception {
        return businessRepo.findAll();
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }
}
