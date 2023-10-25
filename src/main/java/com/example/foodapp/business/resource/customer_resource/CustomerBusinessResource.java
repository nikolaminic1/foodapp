package com.example.foodapp.business.resource.customer_resource;

import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/restaurant")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerBusinessResource {
    private final CustomerRestaurantService customerRestaurantService;
}
