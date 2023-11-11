package com.example.foodapp.product.service.business;


import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public interface OwnerProductService {
    Product create(ProductRequest productRequest, Principal principal);
    PaginatedResponse<Product> list(Integer limit, Integer per_page, Integer order, Integer visible, Principal principal) throws Exception;
    List<Product> getMyList(Principal principal);
    Product get(Long id);
    Product get(Principal principal, Long id) throws Exception;
    Product update(ProductRequest productRequest,Long id , Principal principal);
    Boolean delete(Long id);
}
