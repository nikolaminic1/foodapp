package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.admin.AdminProductService;
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
public class ProductServiceImplementation implements AdminProductService {
    private final ProductRepo productRepo;

    @Override
    public Product create(ProductRequest productRequest, Principal principal) {
        return null;
    }

    @Override
    public Collection<Product> list(int limit) {
        return productRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Product get(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product update(Long id) {
        Product product = productRepo.findById(id).get();
        return productRepo.save(product);
    }

    @Override
    public Boolean delete(Long id) {
        productRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
