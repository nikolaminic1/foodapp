package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Request.OrderCustomerUpdateRequest;

import java.security.Principal;

public interface CustomerOrderService {
    OrderO create(OrderO orderO);
    OrderO getActiveOrder(Principal principal) throws Exception;
    OrderO get(Long id);
    OrderO update(Long id, OrderCustomerUpdateRequest request, Principal principal) throws Exception;
    Boolean delete(Long id);
    OrderO orderingOrder(OrderO orderO);
}
