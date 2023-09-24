package com.example.foodapp.business.model.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerBusinessRatingRequest {
    Long restaurantId;
    Integer rating;
}
