package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.SideDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SideDishRepo extends JpaRepository<SideDish, Long> {
    Optional<SideDish> findById(Long id);
}
