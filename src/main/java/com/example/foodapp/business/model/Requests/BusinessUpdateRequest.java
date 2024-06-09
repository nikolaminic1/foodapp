package com.example.foodapp.business.model.Requests;

import com.example.foodapp.business.model.BusinessTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Double priceOfDelivery;
    private Double priceOfOrderForFreeDelivery;
    private List<Long> tags;
    private TimeOpenedWeekRequest timeOpened;
}

