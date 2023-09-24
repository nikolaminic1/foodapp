package com.example.foodapp.product.service.business;

import com.example.foodapp.product.model.ProductTag;
import com.example.foodapp.product.model.Request.ProductTagRequest;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public interface OwnerProductTagService {
    List<ProductTag> create(ProductTagRequest productTagRequest, Principal principal);
    Collection<ProductTag> list(int limit);
    ProductTag get(Long id);
    ProductTag update(Long id);
    Boolean delete(Long id);
}
