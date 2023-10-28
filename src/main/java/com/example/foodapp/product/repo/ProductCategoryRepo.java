package com.example.foodapp.product.repo;

//import com.example.foodapp.business.model.Business;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findById(Long id);
    Optional<ProductCategory> findProductCategoryByIdAndBusiness_BusinessOwner(@NonNull Long id, @NonNull BusinessOwner owner);
    void deleteProductCategoryById(Long id);
    List<ProductCategory> findProductCategoriesByBusiness(Business business);
}
