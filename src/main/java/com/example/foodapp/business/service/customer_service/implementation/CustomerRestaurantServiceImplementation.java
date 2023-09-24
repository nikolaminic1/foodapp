package com.example.foodapp.business.service.customer_service.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessRating;
import com.example.foodapp.business.model.Requests.CustomerBusinessRatingRequest;
import com.example.foodapp.business.repo.BusinessRatingRepo;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantRatingService;
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
public class CustomerRestaurantServiceImplementation implements CustomerRestaurantRatingService {
    private final _UserProfileService userProfileService;
    private final BusinessRatingRepo businessRatingRepo;
    private final UserRepository userRepo;
    private final BusinessRepo businessRepo;
    private final BusinessRatingRepo ratingRepo;

    @Override
    public BusinessRating get(Long id) throws Exception {
        return ratingRepo.findById(id).orElseThrow(() -> new Exception("Rating does not exists"));
    }

    @Override
    public List<BusinessRating> list(Long businessId) throws Exception {
        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new Exception("Business does not exists"));
        return businessRatingRepo.findBusinessRatingsByBusiness(business);
    }

    @Override
    public BusinessRating create(CustomerBusinessRatingRequest request, Principal principal) throws Exception {
        if (request.getRestaurantId() == null){
            throw new Exception("You need to specify restaurant id");
        }

        if (request.getRating() == null) {
            throw new Exception("You need to specify rating");
        }

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user.getUserProfile());

        Business business = businessRepo.findById(request.getRestaurantId())
                .orElseThrow(() -> new Exception("This business does not exists"));

        BusinessRating businessRating = new BusinessRating();
        businessRating.setBusiness(business);
        businessRating.setCustomer(customer);
        businessRating.setRating(request.getRating());
        businessRatingRepo.save(businessRating);
        calculateAverageRating(business);
        return businessRating;
    }

    @Override
    public BusinessRating update(CustomerBusinessRatingRequest request, Principal principal) throws Exception {
        if (request.getRestaurantId() == null){
            throw new Exception("You need to specify restaurant id");
        }

        if (request.getRating() == null) {
            throw new Exception("You need to specify rating");
        }

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user.getUserProfile());

        Business business = businessRepo.findById(request.getRestaurantId())
                .orElseThrow(() -> new Exception("This business does not exists"));

        BusinessRating businessRating = businessRatingRepo.findBusinessRatingByCustomerAndBusiness(customer, business)
                .orElseThrow();

        businessRating.setBusiness(business);
        businessRating.setCustomer(customer);
        businessRating.setRating(request.getRating());
        businessRatingRepo.save(businessRating);
        calculateAverageRating(business);
        return businessRating;
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }

    public void calculateAverageRating(Business business) {
        List<BusinessRating> businessRatingList = businessRatingRepo.findBusinessRatingsByBusiness(business);
        int totalRating = 0;
        for (BusinessRating rating : businessRatingList) {
            totalRating =+ rating.getRating();
        }

        double averageRating = totalRating / businessRatingList.size();

        log.warn(averageRating);

        business.setAverageRating(averageRating);
        businessRepo.save(business);
    }
}
