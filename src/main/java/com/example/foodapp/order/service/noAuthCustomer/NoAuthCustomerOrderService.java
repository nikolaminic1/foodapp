package com.example.foodapp.order.service.noAuthCustomer;


import com.example.foodapp.order.model.OrderO;

import java.security.Principal;

public interface NoAuthCustomerOrderService {
    OrderO create(OrderO orderO);
    OrderO getActiveOrder(Principal principal) throws Exception;
    OrderO get(Long id);
    OrderO update(Long id);
    Boolean delete(Long id);
}
