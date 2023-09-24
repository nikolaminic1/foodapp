package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.OrderO;

public interface BusinessOrderService {
    OrderO create(OrderO orderO);
    OrderO get(Long id);
    OrderO update(Long id);
    Boolean delete(Long id);
}
