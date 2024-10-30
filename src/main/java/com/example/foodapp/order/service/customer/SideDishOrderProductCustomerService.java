package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.model.Request.AddSideDishToProductRequest;
import com.example.foodapp.product.model.SideDish;

import java.security.Principal;

public interface SideDishOrderProductCustomerService {
    void addToOrderProduct(AddAppendixRequest addAppendixRequest, Principal principal) throws Exception;
    SideDish get(Long id);
    SideDish update(OrderProduct orderProduct, Principal principal);
    Boolean delete(Long id, Principal principal);
    String initializeOrderProduct(Long id, Principal principal) throws Exception;

    String orderProductMapping(OrderProduct orderProduct) throws Exception;
    String addSideDishToOrderProduct (Long orderProductId,
                                      AddSideDishToProductRequest sideDishToProductRequest,
                                      Principal principal) throws Exception;
}
