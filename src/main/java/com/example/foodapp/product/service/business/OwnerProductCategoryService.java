package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;

import java.security.Principal;
import java.util.List;

public interface OwnerProductCategoryService {
    ProductCategory create(ProductCategoryRequest payload, Principal principal);
    ProductCategory get(Long id);
    ProductCategory get(Principal principal, Long id) throws Exception;
    List<ProductCategory> getMyList(Principal principal) throws Exception;
    ProductCategory update(Long id);
    Boolean delete(Long id, Principal principal);
}
