package com.example.foodapp.order.service.admin.implementation;

import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.service.admin.AdminOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminOrderServiceImplementation implements AdminOrderService {
    @Override
    public OrderO create(OrderO orderO) {
        return null;
    }

    @Override
    public OrderO get(Long id) {
        return null;
    }

    @Override
    public OrderO update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
