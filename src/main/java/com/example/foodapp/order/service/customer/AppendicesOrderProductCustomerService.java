package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.product.model.Appendices;

import java.security.Principal;

public interface AppendicesOrderProductCustomerService {
    void addToOrderProduct(AddAppendixRequest addAppendixRequest, Principal principal) throws Exception;
    Appendices get(Long id);
    Appendices update(OrderProduct orderProduct, Principal principal);
    Boolean delete(Long id, Principal principal);
    String initializeOrderProduct(Long id, Long businessId, Principal principal) throws Exception;
}
