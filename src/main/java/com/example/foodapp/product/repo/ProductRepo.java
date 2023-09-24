package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Collection<Product> findProductsByProductCategory(ProductCategory productCategory);
}
