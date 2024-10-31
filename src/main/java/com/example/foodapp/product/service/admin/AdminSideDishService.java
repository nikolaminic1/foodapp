package com.example.foodapp.product.service.admin;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.model.Request.SideDishRequest;

import java.security.Principal;

public interface AdminSideDishService {
    String createAdmin(SideDishRequest sideDishRequest) throws Exception;
    String create(SideDishRequest sideDishRequest, Principal principal) throws Exception;
    String list(int page, int limit, Principal principal) throws Exception;
    String get(Long id) throws Exception;
    String update(Long id) throws Exception;
    Boolean delete(Long id) throws Exception;
}
