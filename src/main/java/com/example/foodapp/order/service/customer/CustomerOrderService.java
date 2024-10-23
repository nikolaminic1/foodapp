package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Request.OrderCustomerUpdateRequest;

import java.security.Principal;

public interface CustomerOrderService {
    String create(OrderO orderO, Principal principal) throws Exception ;
    String getActiveOrder(Principal principal) throws Exception;
    String get(Long id, Principal principal) throws Exception ;
    String getOrder(Long id, Principal principal) throws Exception ;
    String update(Long id, OrderCustomerUpdateRequest request, Principal principal) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception ;
    String orderingOrder(OrderO orderO, Principal principal) throws Exception ;
}
