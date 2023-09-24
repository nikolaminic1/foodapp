package com.example.foodapp.order.service.noAuthCustomer;


import com.example.foodapp.order.model.Refund;

public interface NoAuthCustomerRefundService {
    Refund create(Refund refund);
    Refund get(Long id);
    Refund update(Long id);
    Boolean delete(Long id);
}
