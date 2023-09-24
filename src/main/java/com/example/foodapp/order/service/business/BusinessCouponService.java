package com.example.foodapp.order.service.business;


import com.example.foodapp.order.model.Coupon;

public interface BusinessCouponService {
    Coupon create(Coupon coupon);
    Coupon get(Long id);
    Coupon update(Long id);
    Boolean delete(Long id);
}
