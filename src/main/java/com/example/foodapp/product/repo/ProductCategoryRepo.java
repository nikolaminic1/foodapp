package com.example.foodapp.product.repo;

//import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findById(Long id);
    void deleteProductCategoryById(Long id);
//    List<ProductCategory> findProductCategoriesByBusiness(Business business);
}
