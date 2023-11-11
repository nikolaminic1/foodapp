package com.example.foodapp.business.repo;

import com.example.foodapp.business.model.BusinessTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessTagRepo extends JpaRepository<BusinessTag, Long> {
    Optional<BusinessTag> findBusinessTagById(Long id);
}
