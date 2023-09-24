package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.ProductImage;

import java.util.Collection;

public interface AdminProductImageService {
    ProductImage create(ProductImage business);
    Collection<ProductImage> list(int limit);
    ProductImage get(Long id);
    ProductImage update(Long id);
    Boolean delete(Long id);
}
