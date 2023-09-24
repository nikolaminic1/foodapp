package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDescriptionRepo extends JpaRepository<ProductDescription, Long> {
    Optional<ProductDescription> findById(Long id);
}
