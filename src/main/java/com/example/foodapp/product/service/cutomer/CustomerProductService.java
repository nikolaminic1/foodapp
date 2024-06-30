package com.example.foodapp.product.service.cutomer;

import org.springframework.data.domain.Page;

import java.security.Principal;

public interface CustomerProductService {
    String get (Long id, Principal principal) throws Exception;
}
