package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Appendices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppendicesRepo extends JpaRepository<Appendices, Long> {
    Optional<Appendices> findById(Long id);
}
