package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.model.Request.OrderProductUpdateRequest;
import com.example.foodapp.product.model.Product;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface CustomerOrderProductService {
    OrderProduct create(OrderProductRequest orderProductRequest, Principal principal) throws Exception;
    OrderProduct get(Long id);
    OrderProduct update(OrderProductUpdateRequest orderProductUpdateRequest, Principal principal) throws Exception;
    Boolean delete(Long id);
    void addAppendicesToOrderProduct(Product product, Map<Long, List<Long>> data, OrderProduct orderProduct) throws Exception;
//    void addProductVariations(Product product, OrderProduct orderProduct, Long productVariationId) throws Exception;
}
