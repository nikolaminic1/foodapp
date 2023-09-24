package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.AddressRepo;
import com.example.foodapp.auth.repo.BillingAddressRepo;
import com.example.foodapp.auth.repo.ShippingAddressRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.Addresses.BillingAddress;
import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderCustomerUpdateRequest;
import com.example.foodapp.order.repo.CouponRepo;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.customer.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerOrderServiceImplementation implements CustomerOrderService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;
    private final CouponRepo couponRepo;
    private final AddressRepo addressRepo;
    private final ShippingAddressRepo shippingAddressRepo;
    private final BillingAddressRepo billingAddressRepo;

    @Override
    public OrderO create(OrderO orderO) {
        return null;
    }

    @Override
    public OrderO getActiveOrder(Principal principal) throws Exception{
        UserProfile userProfile = userRepo.findByEmail(principal.getName()).orElseThrow().getUserProfile();
        Customer customer = userProfileService.returnCustomer(userProfile);
        if(orderRepo.findOrderOByCustomerAndOrdered(customer, false).isPresent()){
            return orderRepo.findOrderOByCustomerAndOrdered(customer, false).get();
        } else {
            throw new Exception("Order not found");
        }
    }

    @Override
    public OrderO orderingOrder(OrderO orderO) {
        List<OrderProduct> orderProductList = orderProductRepo.findOrderProductsByOrderO(orderO);
        for(OrderProduct orderProduct : orderProductList){
            orderProduct.setTimeUpdated(now());
            orderProduct.setOrdered(true);
            orderProduct.setTimeOrdered(now());
        }
        orderProductRepo.saveAll(orderProductList);

        orderO.setTimeUpdated(now());
        orderO.setOrderedTime(now());
        orderO.setOrdered(true);

        if(orderO.getCoupon() != null){
            Coupon coupon = orderO.getCoupon();
            if(!coupon.isApplied()){
                coupon.setApplied(true);
                couponRepo.save(coupon);
            }
        }

        orderRepo.save(orderO);
        return orderO;
    }

    @Override
    public OrderO get(Long id) {
        return null;
    }

    @Override
    public OrderO update(Long id, OrderCustomerUpdateRequest request, Principal principal) throws Exception {
        Customer customer = userProfileService.returnCustomer(userRepo.findByEmail(principal.getName()).orElseThrow().getUserProfile());
        OrderO orderO = orderRepo.findOrderOByCustomerAndOrdered(customer, false)
                .orElseThrow(() -> new Exception("Order does not exists"));
        if(!Objects.equals(orderO.getId(), id)){
            throw new Exception("This order does not belong to you");
        }

        Boolean ordered = request.getOrdered();
        Long shippingAddressId = request.getShippingAddressId();
        Long billingAddressId = request.getBillingAddressId();


        if(shippingAddressId != null){
            ShippingAddress shippingAddress = shippingAddressRepo.findShippingAddressById(id)
                    .orElseThrow(() -> new Exception("Shipping address does not exits"));
//            Address address = addressRepo.findAddressByShippingAddresses(shippingAddress)
//                    .orElseThrow(() -> new Exception("Address does not exists"));
//            UserProfile addressUser = userRepo.findByUsername(principal.getName()).getUserProfile();
//
//            if(addressUser != address.getUserProfile()){
//                throw new Exception("This address does not belong to you");
//            }

            orderO.setShippingAddress(shippingAddress);
        }

//        if(billingAddressId != null){
//            BillingAddress billingAddress = billingAddressRepo.findBillingAddressById(id)
//                    .orElseThrow(() -> new Exception("Billing address does not exits"));
//            Address address = addressRepo.findAddressByBillingAddresses(billingAddress)
//                    .orElseThrow(() -> new Exception("Address does not exists"));
//            UserProfile addressUser = userRepo.findByUsername(principal.getName()).getUserProfile();
//
//            if(addressUser != address.getUserProfile()){
//                throw new Exception("This address does not belong to you");
//            }

//            orderO.setBillingAddress(billingAddress);
//        }


        if(ordered){
            if(orderO.getShippingAddress() == null){
                throw new Exception("You need to enter shipping address");
            }

            if(orderO.getBillingAddress() == null){
                throw new Exception("You need to enter billing address");
            }
            orderO.setOrdered(true);
        }

        orderRepo.save(orderO);

        return orderO;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
