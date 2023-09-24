package com.example.foodapp.order.model.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderProductUpdateRequest {
    private Long orderProductId;
    private Long productVariationId;
    private int quantity;
}
