package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.ProductVariation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductVariationRepo extends JpaRepository<ProductVariation, Long> {
    @NotNull
    Optional<ProductVariation> findById(Long id);
}
