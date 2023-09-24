package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Variation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariationRepo extends JpaRepository<Variation, Long> {
    Optional<Variation> findById(Long id);
    List<Variation> findVariationsByProduct(Product product);
    boolean existsVariationByNameAndProduct(String name, Product product);
}
