package com.example.foodapp.order.repo;

import com.example.foodapp.order.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(Long id);
}
