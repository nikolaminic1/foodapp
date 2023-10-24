package com.example.foodapp.order.service.noAuthCustomer.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class NoAuthCustomerOrderServiceImplementation implements NoAuthCustomerOrderService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderRepo orderRepo;

    @Override
    public OrderO create(OrderO orderO) {
        return null;
    }

    @Override
    public OrderO getActiveOrder(Principal principal) throws Exception{
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);
        if(orderRepo.findOrderOByCustomerAndOrdered(customer, false).isPresent()){
            return orderRepo.findOrderOByCustomerAndOrdered(customer, false).get();
        } else {
            throw new Exception("Order not found");
        }
    }

    @Override
    public OrderO get(Long id) {
        return null;
    }

    @Override
    public OrderO update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
