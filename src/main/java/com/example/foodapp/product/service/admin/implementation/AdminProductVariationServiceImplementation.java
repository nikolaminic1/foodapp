package com.example.foodapp.product.service.admin.implementation;


import com.example.foodapp.product.model.ProductVariation;
import com.example.foodapp.product.repo.ProductVariationRepo;
import com.example.foodapp.product.service.admin.AdminProductVariationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminProductVariationServiceImplementation implements AdminProductVariationService {
    private final ProductVariationRepo productVariationRepo;

    @Override
    public ProductVariation create(ProductVariation productVariation) {
        return null;
    }

    @Override
    public Collection<ProductVariation> list(int limit) {
        return null;
    }

    @Override
    public ProductVariation get(Long id) {
        return null;
    }

    @Override
    public ProductVariation update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
