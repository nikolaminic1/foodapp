package com.example.foodapp.order.service.noAuthCustomer.implementation;

import com.example.foodapp.order.model.Payment;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class NoAuthCustomerPaymentServiceImplementation implements NoAuthCustomerPaymentService {
    @Override
    public Payment create(Payment payment) {
        return null;
    }

    @Override
    public Payment get(Long id) {
        return null;
    }

    @Override
    public Payment update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
