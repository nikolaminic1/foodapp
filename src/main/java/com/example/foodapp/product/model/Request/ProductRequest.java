package com.example.foodapp.product.model.Request;


import com.example.foodapp.product.enumeration.Availability;
import lombok.Data;

import java.util.List;


@Data
public class ProductRequest {
    private Long product_id;
    private String codeOfProduct;
    private String nameOfProduct;
    private double priceOfProduct;
    private double discountPrice;
    private boolean isOnDiscount;
    private String aboutProduct;
    private int preparationTime;
    private Availability availability;
    private boolean productVisible;
    private String productCategory;
    private List<SideDishCategoryRequest> sideDishCategories;

    public Long getId() {
        return this.product_id;
    }
}

