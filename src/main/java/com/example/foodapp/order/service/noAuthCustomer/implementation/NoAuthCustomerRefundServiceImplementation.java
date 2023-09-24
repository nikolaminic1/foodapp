package com.example.foodapp.order.service.noAuthCustomer.implementation;


import com.example.foodapp.order.model.Refund;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerRefundService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class NoAuthCustomerRefundServiceImplementation implements NoAuthCustomerRefundService {
    @Override
    public Refund create(Refund refund) {
        return null;
    }

    @Override
    public Refund get(Long id) {
        return null;
    }

    @Override
    public Refund update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
