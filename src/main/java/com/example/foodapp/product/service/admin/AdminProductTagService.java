package com.example.foodapp.product.service.admin;


import com.example.foodapp.product.model.ProductTag;

import java.util.Collection;

public interface AdminProductTagService {
    ProductTag create(ProductTag business);
    Collection<ProductTag> list(int limit);
    ProductTag get(Long id);
    ProductTag update(Long id);
    Boolean delete(Long id);
}
