package com.example.foodapp.order.model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCustomerUpdateRequest {
    private Boolean ordered;
    private Long ShippingAddressId;
    private Long BillingAddressId;
}
