package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Request.BusinessOrderUpdateRequest;

import java.security.Principal;

public interface BusinessOrderService {
    String createOrUpdate(BusinessOrderUpdateRequest orderUpdateRequest, Principal principal) throws Exception;
    String get(Long id, Principal principal) throws Exception;
    String list(Principal principal) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;
}
