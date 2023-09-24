package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.ProductDescription;
import com.example.foodapp.product.repo.ProductDescriptionRepo;
import com.example.foodapp.product.service.admin.AdminProductDescriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminProductDescriptionServiceImplementation implements AdminProductDescriptionService {
    private final ProductDescriptionRepo productDescriptionRepo;

    @Override
    public ProductDescription create(ProductDescription business) {
        return null;
    }

    @Override
    public Collection<ProductDescription> list(int limit) {
        return null;
    }

    @Override
    public ProductDescription get(Long id) {
        return null;
    }

    @Override
    public ProductDescription update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

//
}
