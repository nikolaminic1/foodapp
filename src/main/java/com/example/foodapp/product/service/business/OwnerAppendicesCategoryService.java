package com.example.foodapp.product.service.business;


import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.AppendicesCategoryCreateRequest;

import java.security.Principal;
import java.util.List;

public interface OwnerAppendicesCategoryService {
    AppendicesCategory create(AppendicesCategoryCreateRequest appendicesCategoryCreateRequest, Principal principal) throws Exception;
    AppendicesCategory get(Long id);
    List<AppendicesCategory> getAllAppendicesCategory(Principal principal);
    List<AppendicesCategory> getAppendicesCategoryByProduct(Product product, Principal principal);
    AppendicesCategory update(Long id);
    Boolean delete(Long id, Principal principal);
}
