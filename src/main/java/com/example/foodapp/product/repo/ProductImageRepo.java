package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findById(Long id);

    Optional<ProductImage> findProductImageByProduct(Product product);


}
