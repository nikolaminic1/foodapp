package com.example.foodapp.business.resource.customer_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/restaurant")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerBusinessResource {
    private final CustomerRestaurantService customerRestaurantService;

    @GetMapping("/list")
    public ResponseEntity<Response> getRestaurantsList() {
        try {
            customerRestaurantService.list();
            return ResponseEntity.ok().body(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("restaurants", customerRestaurantService.list()))
                            .status(HttpStatus.OK)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(e.getMessage())
                            .timeStamp(now())
                            .build()
            );

        }
    }

}
