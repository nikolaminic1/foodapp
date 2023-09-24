package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);
}
