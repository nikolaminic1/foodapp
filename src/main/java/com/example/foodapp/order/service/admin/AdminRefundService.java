package com.example.foodapp.order.service.admin;


import com.example.foodapp.order.model.Refund;

public interface AdminRefundService {
    Refund create(Refund refund);
    Refund get(Long id);
    Refund update(Long id);
    Boolean delete(Long id);
}
