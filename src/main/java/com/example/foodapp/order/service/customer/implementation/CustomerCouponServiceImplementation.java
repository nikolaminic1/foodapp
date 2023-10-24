package com.example.foodapp.order.service.customer.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.repo.CouponRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.customer.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerCouponServiceImplementation implements CustomerCouponService {
    private final UserRepository userRepo;
    private final OrderRepo orderRepo;
    private final CouponRepo couponRepo;
    private final _UserProfileService userProfileService;


    @Override
    public Coupon create(Coupon coupon) {
        return null;
    }

    @Override
    public Coupon get(Long id, Principal principal) throws Exception{
        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new Exception("This coupon does not exits"));

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);

//        if(customer == coupon.getCustomer()){
//            return coupon;
//        }

        throw new Exception("This coupon does not belong to you");
    }

    @Override
    public List<Coupon> getList(Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);
        List<Coupon> couponList = couponRepo.findCouponsByCustomer(customer);

        if(couponList.isEmpty()){
            throw new Exception("You do not have coupons");
        }

        return couponList;
    }

    @Override
    public Coupon update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void applyCouponToOrder(String couponCode, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);

        OrderO orderO = orderRepo.findOrderOByCustomerAndOrdered(customer, false)
                .orElseThrow(() -> new Exception("Order does not exists"));
        Coupon coupon = couponRepo.findCouponByCode(couponCode)
                .orElseThrow(() -> new Exception("Coupon does not exists")
        );


//        Coupon coupon = couponRepo.findCouponByCode(couponCode).get();
        boolean isCouponApplied = coupon.isApplied();

        if(isCouponApplied){
            throw new Exception("This coupon is already used");
        }

        LocalDateTime dateExpires = coupon.getDataExpires();

        if(dateExpires.isAfter(now())){
            throw new Exception("This coupon is expired");
        }

        if(orderO.getPrice() <= coupon.getAmount()){
            double activePrice = orderO.getPrice();
            orderO.setPrice(activePrice - coupon.getAmount());
            orderO.setTimeUpdated(now());
            orderO.setCoupon(coupon);
            orderRepo.save(orderO);
        } else {
            throw new Exception("This coupon can not be applied to this order because the amount of coupon is larger than price of order");
        }
    }
}
