package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;

import java.security.Principal;
import java.util.Collection;

public interface AdminProductService {
    Product create(ProductRequest productRequest, Principal principal);
    Collection<Product> list(int limit);
    Product get(Long id);
    Product update(Long id);
    Boolean delete(Long id);
}
