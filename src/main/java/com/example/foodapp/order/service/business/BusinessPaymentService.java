package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.Payment;

public interface BusinessPaymentService {
    Payment create(Payment payment);
    Payment get(Long id);
    Payment update(Long id);
    Boolean delete(Long id);
}
