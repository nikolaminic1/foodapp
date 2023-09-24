package com.example.foodapp.order.service.admin;


import com.example.foodapp.order.model.OrderO;

public interface AdminOrderService {
    OrderO create(OrderO orderO);
    OrderO get(Long id);
    OrderO update(Long id);
    Boolean delete(Long id);
}
