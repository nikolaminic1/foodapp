package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;
import com.example.foodapp.product.model.Request.ProductCategoryUpdateRequest;

import java.security.Principal;
import java.util.List;

public interface OwnerProductCategoryService {
    ProductCategory create(ProductCategoryRequest payload, Principal principal);
    String get(Principal principal, Long id) throws Exception;
    String getMyList(Principal principal) throws Exception;
    String update(ProductCategoryUpdateRequest request, Principal principal) throws Exception;
    String delete(String id, Principal principal) throws Exception;
}
