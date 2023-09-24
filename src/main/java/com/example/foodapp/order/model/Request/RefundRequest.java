package com.example.foodapp.order.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundRequest {
    private Long orderId;
    private String reason;
    private String email;
}
