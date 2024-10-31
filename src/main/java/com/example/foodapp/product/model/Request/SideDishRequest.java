package com.example.foodapp.product.model.Request;

import lombok.Data;

@Data
public class SideDishRequest {
    private Long id;
    private String nameOfSideDish;
    private Boolean doesAffectPrice;
    private int price;
    private Long productId;
}
