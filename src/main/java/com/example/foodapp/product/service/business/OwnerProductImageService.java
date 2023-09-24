package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.ProductImage;

import java.util.Collection;

public interface OwnerProductImageService {
    ProductImage create(ProductImage business);
    Collection<ProductImage> list(int limit);
    ProductImage get(Long id);
    ProductImage update(Long id);
    Boolean delete(Long id);
}
