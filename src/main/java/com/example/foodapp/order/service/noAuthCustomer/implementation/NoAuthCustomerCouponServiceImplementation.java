package com.example.foodapp.order.service.noAuthCustomer.implementation;


import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerCouponService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class NoAuthCustomerCouponServiceImplementation implements NoAuthCustomerCouponService {
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
