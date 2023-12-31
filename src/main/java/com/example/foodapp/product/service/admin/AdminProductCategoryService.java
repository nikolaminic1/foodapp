package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;

import java.security.Principal;
import java.util.List;

public interface AdminProductCategoryService {
    ProductCategory create(ProductCategoryRequest payload, Principal principal);
    ProductCategory get(Long id);
    List<ProductCategory> getMyList(Principal principal);
    ProductCategory update(Long id);
    Boolean delete(Long id, Principal principal);
}
