package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.ProductDescription;

import java.util.Collection;

public interface AdminProductDescriptionService {
    ProductDescription create(ProductDescription business);
    Collection<ProductDescription> list(int limit);
    ProductDescription get(Long id);
    ProductDescription update(Long id);
    Boolean delete(Long id);
}
