package com.example.foodapp.order.service.admin;


import com.example.foodapp.order.model.OrderProduct;

public interface AdminOrderProductService {
    OrderProduct create(OrderProduct orderProduct);
    OrderProduct get(Long id);
    OrderProduct update(Long id);
    Boolean delete(Long id);
}
