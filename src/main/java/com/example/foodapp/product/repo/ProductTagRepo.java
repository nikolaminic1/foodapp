package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTagRepo extends JpaRepository<ProductTag, Long> {
    Optional<ProductTag> findById(Long id);
    List<ProductTag> findProductTagsByProduct(Product product);
    boolean existsByNameAndProduct(String name, Product product);
}
