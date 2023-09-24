package com.example.foodapp.order.repo;

import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefundRepo extends JpaRepository<Refund, Long> {
    Optional<Refund> findById(Long id);
    List<Refund> findRefundsByCustomer(Customer customer);
}
