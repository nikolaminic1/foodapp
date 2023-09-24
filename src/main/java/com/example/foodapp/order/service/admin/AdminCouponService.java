package com.example.foodapp.order.service.admin;


import com.example.foodapp.order.model.Coupon;

public interface AdminCouponService {
    Coupon create(Coupon coupon);
    Coupon get(Long id);
    Coupon update(Long id);
    Boolean delete(Long id);
}
