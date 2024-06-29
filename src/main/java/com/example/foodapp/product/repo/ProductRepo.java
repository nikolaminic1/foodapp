package com.example.foodapp.product.repo;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> , JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByIdAndProductCategory_Business_BusinessOwner_User(Long id, User user);
    Collection<Product> findProductsByProductCategory(ProductCategory productCategory);
    Collection<Product> findProductsByProductCategory_Business(Business business);
    Page<Product> findProductsByProductCategory_BusinessAndProductVisible(Business business, Boolean visible, Pageable pageable);
    Page<Product> findProductsByProductCategory_Business(Business business, Pageable pageable);
}
