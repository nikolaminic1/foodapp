package com.example.foodapp.order.service.business.implementation;


import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.service.business.BusinessCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class BusinessCouponServiceImplementation implements BusinessCouponService {
    @Override
    public Coupon create(Coupon coupon) {
        return null;
    }

    @Override
    public Coupon get(Long id) {
        return null;
    }

    @Override
    public Coupon update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
