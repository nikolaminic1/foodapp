package com.example.foodapp.order.service.business.implementation;


import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.BusinessOrderUpdateRequest;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.serializer.business.BusinessOrderSerializer;
import com.example.foodapp.order.service.business.BusinessOrderService;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class BusinessOrderServiceImplementation implements BusinessOrderService {
    private final UserRepository userRepository;
    private final BusinessRepo businessRepo;
    private final OrderRepo orderRepo;
    private final BusinessOwnerRepo businessOwnerRepo;

    @Override
    public String createOrUpdate(BusinessOrderUpdateRequest orderUpdateRequest, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String get(Long id, Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        OrderO order = orderRepo.findOrderOById(id)
                .orElseThrow(() -> new Exception("Order not found"));
        List<OrderProduct> products = order.getProductList();
        Business business;
        if (products.size() == 0) {
            throw new Exception("There are no products in current order");
        } else {
             business = products.get(0).getProduct().getProductCategory().getBusiness();
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(OrderO.class, new BusinessOrderSerializer.DetailSerializer());
        mapper.registerModule(module);
        if (business.getBusinessOwner().getUser() == user){
            return mapper.writeValueAsString(order);
        } else {
            throw new Exception("This order does not belong to you");
        }
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String list(Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        BusinessOwner owner = businessOwnerRepo.findBusinessOwnerByUser(user)
                .orElseThrow(() -> new Exception("Owner does not exist"));
        Business business = businessRepo.findBusinessByBusinessOwner(owner)
                .orElseThrow(() -> new Exception("Business does not exist"));
        List<OrderO> orders = orderRepo.findOrderOSByBusiness(business);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BusinessOrderSerializer.ListSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(orders);
    }
}