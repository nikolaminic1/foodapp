package com.example.foodapp.order.repo;

import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.OrderO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<OrderO, Long> {
    Optional<OrderO> findById(Long id);
    Optional<OrderO> findOrderOByCustomerAndOrdered(Customer customer, Boolean ordered);
    Optional<OrderO> findOrderOByUuid(String uuid);
    List<OrderO> findOrderOSByCustomer(Customer customer);
}
