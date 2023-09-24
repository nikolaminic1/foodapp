package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.Request.VariationRequest;
import com.example.foodapp.product.model.Variation;

import java.security.Principal;
import java.util.Collection;

public interface OwnerVariationService {
    Variation create(VariationRequest variationRequest, Principal principal);
    Collection<Variation> list(int limit);
    Variation get(Long id);
    Variation update(Long id);
    Boolean delete(Long id);
}
