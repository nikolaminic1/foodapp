package com.example.foodapp.product.model.Request;


import com.example.foodapp.product.enumeration.Availability;
import lombok.Data;


@Data
public class ProductRequest {
    private String codeOfProduct;
    private double priceOfProduct;
    private double discountPrice;
    private boolean isOnDiscount;
    private String aboutProduct;
    private int preparationTime;
    private Availability availability;
    private boolean productVisible;
    private long productCategory;
}
