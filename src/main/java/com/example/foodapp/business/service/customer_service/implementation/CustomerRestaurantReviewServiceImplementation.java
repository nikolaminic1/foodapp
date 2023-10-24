package com.example.foodapp.business.service.customer_service.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessReview;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.repo.ReviewRepo;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantReviewService;
//import com.example.foodapp.order.model.OrderO;
//import com.example.foodapp.order.model.OrderProduct;
//import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.repo.OrderRepo;
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
public class CustomerRestaurantReviewServiceImplementation implements CustomerRestaurantReviewService {

    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final ReviewRepo reviewRepo;
    private final BusinessRepo businessRepo;
    private final OrderRepo orderRepo;

    @Override
    public BusinessReview get(Long id) throws Exception {
        return reviewRepo.findById(id).orElseThrow(() -> new Exception("Review does not exists"));
    }

    @Override
    public List<BusinessReview> list(Long businessId) throws Exception {
        Business business = businessRepo.findById(businessId).orElseThrow(() -> new Exception("Business does not exists"));
        return reviewRepo.findBusinessReviewsByBusiness(business);
    }

    @Override
    public BusinessReview create(CustomerReviewRequest request, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);
        List<OrderO> ordersList = orderRepo.findOrderOSByCustomer(customer);

        Business business = businessRepo.findById(request.getBusinessId())
                .orElseThrow(() -> new Exception("Business does not exists"));

        for (OrderO order : ordersList) {
            List<OrderProduct> orderProducts = order.getProductList();
            //TODO to check for every product its business owner and to check if user will be allowed to post review on
            // certain restaurant because it will be only allowed to review those that you bought from
        }

        return null;
    }

    @Override
    public BusinessReview update(Long reviewId, CustomerReviewRequest request, Principal principal) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }
}
