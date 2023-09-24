package com.example.foodapp.product.model.Request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class VariationRequest {
    private String name;
    private Long productId;

}
