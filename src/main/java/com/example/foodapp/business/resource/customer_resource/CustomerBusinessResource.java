package com.example.foodapp.business.resource.customer_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/restaurant")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerBusinessResource {
    private final CustomerRestaurantService customerRestaurantService;

    @JsonView(View.PublicList.class)
    @GetMapping("/list")
    public ResponseEntity<String> getRestaurantsList(@RequestParam(name = "page") String page,
                                                     @RequestParam(name = "limit") String per_page) {
        try {
            return ResponseEntity.ok().body(customerRestaurantService.list(page, per_page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @JsonView(View.PublicDetail.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantDetail(@PathVariable String id) {
        try {
            var restaurant = customerRestaurantService.get(parseLong(id));
            return ResponseEntity.ok().body(restaurant);
//            Response.builder()
//                    .timeStamp(now())
//                    .data(Map.of("restaurant", restaurant))
//                    .status(HttpStatus.OK)
//                    .build()
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
//            Response.builder()
//                    .status(HttpStatus.BAD_REQUEST)
//                    .message(e.getMessage())
//                    .timeStamp(now())
//                    .build()

        }
    }

}
