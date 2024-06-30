package com.example.foodapp.business.service.customer_service.implementation;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp._api.PaginatedResponseSerializer;
import com.example.foodapp.auth.serializers.UserDetailSerializer;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.CustomerReviewRequest;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.serializers.customer.CustomerRestaurantSerializer;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantService;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static com.example.foodapp._api.NumericCheck.isNumeric;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerRestaurantServiceImplementation implements CustomerRestaurantService {
    private final BusinessRepo businessRepo;

    @Override
    public String get(Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Business.class, new CustomerRestaurantSerializer.DetailSerializer());
        mapper.registerModule(module);
        Business business = businessRepo.findBusinessById(id)
                .orElseThrow(() -> new Exception("The restaurant does not exist"));
        return mapper.writeValueAsString(business);
    }

    @Override
    public String list(String page, String per_page) throws Exception {
        int page_n;
        int limit;

        if (isNumeric(page)) {
            page_n = Integer.parseInt(page);
        } else {
            page_n = 0;
        }

        if (isNumeric(per_page)) {
            limit = Integer.parseInt(per_page);
        } else {
            limit = 20;
        }

        Pageable pageable = PageRequest.of(page_n, limit);
        Page<Business> businesses;
        businesses = businessRepo.findBusinessesByActive(true, pageable);
        PaginatedResponse<Business> data = new PaginatedResponse<>(businesses);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(new JavaTimeModule());
        module.addSerializer(PaginatedResponse.class, new PaginatedResponseSerializer());
        module.addSerializer(Business.class, new CustomerRestaurantSerializer.LessDetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(data);
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }
}
