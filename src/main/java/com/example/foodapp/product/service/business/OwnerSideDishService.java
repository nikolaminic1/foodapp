package com.example.foodapp.product.service.business;

import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.SideDishCreateRequest;

import java.security.Principal;
import java.util.List;

public interface OwnerSideDishService {
    SideDish create(SideDishCreateRequest appendicesCreateRequest, Principal principal) throws Exception;
    SideDish get(Long id);
    List<SideDish> getAllSideDish(Principal principal);
    List<SideDish> getSideDishByProduct(Product product, Principal principal);
    SideDish update(Long id);
    Boolean delete(Long id, Principal principal);
}
