package com.example.foodapp.product.repo;

import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppendicesCategoryRepo extends JpaRepository<AppendicesCategory, Long> {
    Optional<AppendicesCategory> findById(Long id);
    Optional<AppendicesCategory> findAppendicesCategoryByAppendicesListContaining(Appendices appendices);
    List<AppendicesCategory> findAppendicesCategoriesByProduct(Product product);
}
