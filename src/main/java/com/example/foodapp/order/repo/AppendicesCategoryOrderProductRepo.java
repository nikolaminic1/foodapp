package com.example.foodapp.order.repo;


import com.example.foodapp.order.model.AppendicesCategoryOrderProduct;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.product.model.AppendicesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppendicesCategoryOrderProductRepo extends JpaRepository<AppendicesCategoryOrderProduct, Long> {
    Optional<AppendicesCategoryOrderProduct> findById(Long id);
    Optional<AppendicesCategoryOrderProduct> findAppendicesCategoryOrderProductByAppendicesCategory(AppendicesCategory appendicesCategory);
    Optional<AppendicesCategoryOrderProduct> findAppendicesCategoryOrderProductByAppendicesCategoryAndOrderProducts(AppendicesCategory appendicesCategory, OrderProduct orderProduct);
}
