package com.example.foodapp.order.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAppendixRequest {
    private Long appendixId;
    private Long orderProductId;
}
