package com.example.foodapp.order.repo;


import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {
    Optional<OrderProduct> findById(Long id);
    List<OrderProduct> findOrderProductsByProductAndProductVariation(Product product, ProductVariation productVariation);
    List<OrderProduct> findOrderProductsByOrderO(OrderO orderO);
}
