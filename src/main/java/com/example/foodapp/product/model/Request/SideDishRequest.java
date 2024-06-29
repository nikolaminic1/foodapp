package com.example.foodapp.product.model.Request;

import lombok.Data;

@Data
public class SideDishRequest {
    private Long id;
    private String nameOfAppendices;
    private Boolean doesAffectPrice;
    private int price;
}
