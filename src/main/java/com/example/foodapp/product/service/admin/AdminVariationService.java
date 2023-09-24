package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.Request.VariationRequest;
import com.example.foodapp.product.model.Variation;

import java.security.Principal;
import java.util.Collection;

public interface AdminVariationService {
    Variation create(VariationRequest variationRequest, Principal principal);
    Collection<Variation> list(int limit);
    Variation get(Long id);
    Variation update(Long id);
    Boolean delete(Long id);
}
