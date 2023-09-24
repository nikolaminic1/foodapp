package com.example.foodapp.order.service.customer;


import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.Payment;
import com.example.foodapp.order.model.Request.PaymentRequest;

import java.security.Principal;

public interface CustomerPaymentService {
    Payment create(PaymentRequest paymentRequest, Principal principal) throws Exception;
    Payment get(Long id);
    Payment update(Long id);
    Boolean delete(Long id);
    void charge(Payment payment, Customer customer, Principal principal, String paymentMethodId) throws Exception;
    String createCustomer(String token, String email) throws Exception;
    com.stripe.model.Customer retrieveCustomer(String id) throws Exception;
}
