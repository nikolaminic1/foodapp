package com.example.foodapp.order.service.noAuthCustomer;


import com.example.foodapp.order.model.Coupon;

public interface NoAuthCustomerCouponService {
    Coupon create(Coupon coupon);
    Coupon get(Long id);
    Coupon update(Long id);
    Boolean delete(Long id);
}
