package com.example.foodapp.product.model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SideDishCreateRequest {
    private String nameOfSideDish;
    private Boolean doesAffectPrice;
    private double price;
    private Long appendicesCategoryId;
}
