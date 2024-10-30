package com.example.foodapp.order.model.Request;

import lombok.Data;

@Data
public class AddSideDishToProductRequest {
    private Long sideDishId;
    private Long sideDishOrderProductCategoryId;
}
