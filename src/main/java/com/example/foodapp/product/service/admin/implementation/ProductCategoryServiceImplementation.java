package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;
import com.example.foodapp.product.repo.ProductCategoryRepo;
import com.example.foodapp.product.service.admin.AdminProductCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class ProductCategoryServiceImplementation implements AdminProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategory create(ProductCategoryRequest payload, Principal principal) {
        return null;
    }

    @Override
    public ProductCategory get(Long id) {
        return null;
    }

    @Override
    public List<ProductCategory> getMyList(Principal principal) {
        return null;
    }

    @Override
    public ProductCategory update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }

}
