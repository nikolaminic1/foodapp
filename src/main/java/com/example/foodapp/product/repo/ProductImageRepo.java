package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findById(Long id);
}
