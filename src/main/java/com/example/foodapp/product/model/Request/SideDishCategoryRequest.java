package com.example.foodapp.product.model.Request;

import lombok.Data;

import java.util.List;

@Data
public class SideDishCategoryRequest {
    private Long id;
    private String nameOfCategory;
    private Boolean isRequired;
    private int numberOfAllowed;
    private List<SideDishRequest> sideDishes;
}

