package com.example.foodapp.order.resource.noAuthCustomer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.service.customer.CustomerOrderProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/unauthorized/customer/order_product")
@RequiredArgsConstructor
@Log4j2
public class NoAuthCustomerOrderProductResource {
    private final CustomerOrderProductService customerOrderProductService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveOrderProduct(Principal principal){
        return ResponseEntity.ok(
                Response.builder()
                .message("OK message")
                .build()
        );
    }
}
