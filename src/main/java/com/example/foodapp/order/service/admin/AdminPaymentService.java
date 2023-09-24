package com.example.foodapp.order.service.admin;


import com.example.foodapp.order.model.Payment;

public interface AdminPaymentService {
    Payment create(Payment payment);
    Payment get(Long id);
    Payment update(Long id);
    Boolean delete(Long id);
}
