package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.ProductImage;
import com.example.foodapp.product.repo.ProductImageRepo;
import com.example.foodapp.product.service.admin.AdminProductImageService;
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
public class AdminProductImageServiceImplementation implements AdminProductImageService {
    private final ProductImageRepo productImageRepo;

    @Override
    public ProductImage create(ProductImage productImage) {
        return productImageRepo.save(productImage);
    }

    @Override
    public Collection<ProductImage> list(int limit) {
        return productImageRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public ProductImage get(Long id) {
        return productImageRepo.findById(id).get();
    }

    @Override
    public ProductImage update(Long id) {
        ProductImage productImage = productImageRepo.findById(id).get();
        return productImageRepo.save(productImage);
    }

    @Override
    public Boolean delete(Long id) {
        productImageRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
