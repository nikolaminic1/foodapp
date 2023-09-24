package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.ProductVariation;
import com.example.foodapp.product.model.Request.ProductVariationRequest;

import java.security.Principal;
import java.util.Collection;

public interface OwnerProductVariationService {
    ProductVariation create(ProductVariationRequest productVariationRequest, Principal principal);
    Collection<ProductVariation> list(int limit);
    ProductVariation get(Long id);
    ProductVariation update(Long id);
    Boolean delete(Long id);
}
