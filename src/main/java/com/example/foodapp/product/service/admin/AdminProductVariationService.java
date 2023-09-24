package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.ProductVariation;

import java.util.Collection;

public interface AdminProductVariationService {
    ProductVariation create(ProductVariation productVariation);
    Collection<ProductVariation> list(int limit);
    ProductVariation get(Long id);
    ProductVariation update(Long id);
    Boolean delete(Long id);
}
