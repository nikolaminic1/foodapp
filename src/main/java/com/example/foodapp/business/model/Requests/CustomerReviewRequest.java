package com.example.foodapp.business.model.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReviewRequest {
    private String review;
    private Long businessId;
}
