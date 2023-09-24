package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.OrderProduct;

public interface BusinessOrderProductService {
    OrderProduct create(OrderProduct orderProduct);
    OrderProduct get(Long id);
    OrderProduct update(Long id);
    Boolean delete(Long id);
}
