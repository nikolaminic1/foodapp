package com.example.foodapp.order.service.noAuthCustomer;


import com.example.foodapp.order.model.Payment;

public interface NoAuthCustomerPaymentService {
    Payment create(Payment payment);
    Payment get(Long id);
    Payment update(Long id);
    Boolean delete(Long id);
}
