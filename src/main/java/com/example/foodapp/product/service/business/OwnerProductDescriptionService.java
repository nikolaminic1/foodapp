package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.ProductDescription;

import java.util.Collection;

public interface OwnerProductDescriptionService {
    ProductDescription create(ProductDescription business);
    Collection<ProductDescription> list(int limit);
    ProductDescription get(Long id);
    ProductDescription update(Long id);
    Boolean delete(Long id);
}
