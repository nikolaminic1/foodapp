package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.AppendicesCategoryOrderProduct;
import com.example.foodapp.order.model.OrderProduct;

import java.security.Principal;

public interface AppendicesCategoryOrderProductService {
    AppendicesCategoryOrderProduct create(OrderProduct orderProduct, Principal principal);
    AppendicesCategoryOrderProduct get(Long id);
    AppendicesCategoryOrderProduct update(OrderProduct orderProduct, Principal principal);
    Boolean delete(Long id);
}
