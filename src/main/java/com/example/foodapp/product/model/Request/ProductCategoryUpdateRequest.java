package com.example.foodapp.product.model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryUpdateRequest {
    private Long id;
    private String nameOfCategory;
    private String descOfCategory;
    private Boolean featured;
    private Boolean categoryVisible;
}
