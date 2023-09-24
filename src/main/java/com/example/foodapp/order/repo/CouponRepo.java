package com.example.foodapp.order.repo;


import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findById(Long id);
    Optional<Coupon> findCouponByCode(String code);
    List<Coupon> findCouponsByCustomer(Customer customer);
}
