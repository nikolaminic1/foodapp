package com.example.foodapp.product.model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppendicesCategoryCreateRequest {
    private String nameOfCategory;
    private Boolean isRequired;
    private Long productId;
    private int numberOfAllowed;
}
