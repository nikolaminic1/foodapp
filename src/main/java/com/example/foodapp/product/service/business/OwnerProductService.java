package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;

import java.security.Principal;
import java.util.Collection;

public interface OwnerProductService {
    Product create(ProductRequest productRequest, Principal principal);
    Collection<Product> list(int limit);
    Collection<Product> getMyList(Principal principal);
    Product get(Long id);
    Product update(ProductRequest productRequest,Long id , Principal principal);
    Boolean delete(Long id);
}
