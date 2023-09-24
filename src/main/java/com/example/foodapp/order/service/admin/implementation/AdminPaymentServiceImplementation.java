package com.example.foodapp.order.service.admin.implementation;


import com.example.foodapp.order.model.Payment;
import com.example.foodapp.order.service.admin.AdminPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminPaymentServiceImplementation implements AdminPaymentService {
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
