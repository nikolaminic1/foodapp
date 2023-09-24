package com.example.foodapp.order.service.customer;


import com.example.foodapp.order.model.Coupon;

import java.security.Principal;
import java.util.List;

public interface CustomerCouponService {
    Coupon create(Coupon coupon);
    Coupon get(Long id, Principal principal) throws Exception ;
    List<Coupon> getList(Principal principal) throws Exception ;
    Coupon update(Long id);
    Boolean delete(Long id);
    void applyCouponToOrder(String couponId, Principal principal) throws Exception;
}
