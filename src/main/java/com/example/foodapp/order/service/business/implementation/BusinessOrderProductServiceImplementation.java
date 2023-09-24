package com.example.foodapp.order.service.business.implementation;

import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.service.business.BusinessOrderProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class BusinessOrderProductServiceImplementation implements BusinessOrderProductService {
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return null;
    }

    @Override
    public OrderProduct get(Long id) {
        return null;
    }

    @Override
    public OrderProduct update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
