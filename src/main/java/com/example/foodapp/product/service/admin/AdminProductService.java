package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AdminProductService {
    Product create(ProductRequest productRequest, Principal principal) throws Exception;
    String list(int page, int limit, Principal principal) throws Exception;
    String get(Long id) throws Exception;
    Product update(Long id) throws Exception;
    Boolean delete(Long id) throws Exception;
    List<Map<String, Object>> getInitial(Long businessId, Principal principal) throws Exception;
}
