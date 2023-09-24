package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.Refund;

public interface BusinessRefundService {
    Refund create(Refund refund);
    Refund get(Long id);
    Refund update(Long id);
    Boolean delete(Long id);
}
