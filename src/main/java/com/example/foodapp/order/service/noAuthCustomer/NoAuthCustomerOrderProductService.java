package com.example.foodapp.order.service.noAuthCustomer;

import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderProductRequest;

public interface NoAuthCustomerOrderProductService {
    OrderProduct create(OrderProductRequest orderProductRequest) throws Exception;
    OrderProduct get(Long id);
    OrderProduct update(Long id);
    Boolean delete(Long id);
}
