package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.Refund;
import com.example.foodapp.order.model.Request.RefundRequest;

import java.security.Principal;
import java.util.List;

public interface CustomerRefundService {
    Refund create(RefundRequest refundRequest, Principal principal) throws Exception;
    Refund get(Long id, Principal principal) throws Exception;
    List<Refund> list(Principal principal) throws Exception;
    Refund update(Long id) throws Exception;
    Boolean delete(Long id) throws Exception;
}
