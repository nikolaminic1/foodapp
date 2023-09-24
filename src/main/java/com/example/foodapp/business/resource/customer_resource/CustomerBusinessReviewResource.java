package com.example.foodapp.business.resource.customer_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.model.BusinessReview;
import com.example.foodapp.business.service.customer_service.service.CustomerRestaurantReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/business_review")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerBusinessReviewResource {

    private final CustomerRestaurantReviewService customerRestaurantReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getSingleRating(@PathVariable Long id) {

        try {
            BusinessReview review = customerRestaurantReviewService.get(id);

            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .timeStamp(now())
                            .data(Map.of("rating", review))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.builder()
                                    .message(e.getMessage())
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .timeStamp(now())
                                    .build()
                    );
        }
    }

    @GetMapping("/get_ratings/{businessId}")
    public ResponseEntity<Response> getRatings(@PathVariable Long businessId) {

        try {
            List<BusinessReview> reviewList = customerRestaurantReviewService.list(businessId);

            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .timeStamp(now())
                            .data(Map.of("rating", reviewList))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.builder()
                                    .message(e.getMessage())
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .timeStamp(now())
                                    .build()
                    );
        }
    }

}
