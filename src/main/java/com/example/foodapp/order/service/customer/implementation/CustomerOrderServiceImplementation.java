package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.AddressRepo;
import com.example.foodapp.auth.repo.BillingAddressRepo;
import com.example.foodapp.auth.repo.ShippingAddressRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.CustomerRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.serializers.BusinessListSerializer;
import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderCustomerUpdateRequest;
import com.example.foodapp.order.repo.CouponRepo;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.serializer.customer.CustomerOrderSerializer;
import com.example.foodapp.order.service.customer.CustomerOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import javax.persistence.criteria.Order;
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
    private final CustomerRepository customerRepository;
    private final BusinessRepo businessRepo;

    @Override
    public String create(OrderO orderO, Principal principal) throws Exception  {
        return null;
    }

    @Override
    public String getOrder(Long id, Principal principal) throws Exception  {
        return null;
    }

    @Override
    public String getActiveOrder(Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(OrderO.class, new CustomerOrderSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(orderRepo.findOrderOByCustomerAndOrdered(customer, false)
                .orElseThrow(() -> new Exception("Order not found")));

    }

    @Override
    public String orderingOrder(OrderO orderO, Principal principal) {

        return null;
    }

    @Override
    public String get(Long id, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        OrderO orderO = orderRepo.findOrderOById(id).orElseThrow(() -> new Exception("Order not found"));

        if (orderO.getCustomer().getUser() != user) {
            throw new Exception("This order does not belong to you");
        }

        return null;
    }

    @Override
    public String update(Long id, OrderCustomerUpdateRequest request, Principal principal) throws Exception {

        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String getRestaurantOrder(Long businessId, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        log.error(1);
        Business business = businessRepo.findBusinessById(businessId)
                .orElseThrow(() -> new Exception("Business not found"));
        log.error(2);
        Customer customer = customerRepository.findCustomerByUser(user)
                .orElseThrow(() -> new Exception("Customer not found."));
        log.error(3);
        OrderO orderO;
        log.error(4);
        if (orderRepo.findOrderOByCustomerAndOrderedAndBusiness(customer, false, business).isPresent()) {
            orderO = orderRepo.findOrderOByCustomerAndOrderedAndBusiness(customer, false, business).get();
            log.error(5);
        } else {
            orderO = new OrderO();
            orderO.setOrdered(false);
            orderO.setPrepared(false);
            orderO.setDelivered(false);
            orderO.setPickedUp(false);
            orderO.setRefundGranted(false);
            orderO.setRefundRequested(false);
            orderO.setCustomer(customer);
            orderO.setBusiness(business);
            orderO.setStartTime(now());
            orderRepo.save(orderO);
            log.error(6);
        }
        log.error(1);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(OrderO.class, new CustomerOrderSerializer.DetailSerializer());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(module);
        return mapper.writeValueAsString(orderO);
    }
}


// update

//    Customer customer = userProfileService.returnCustomer(userRepo.findByEmail(principal.getName()).orElseThrow());
//    OrderO orderO = orderRepo.findOrderOByCustomerAndOrdered(customer, false)
//            .orElseThrow(() -> new Exception("Order does not exists"));
//        if(!Objects.equals(orderO.getId(), id)){
//                throw new Exception("This order does not belong to you");
//                }
//
//                Boolean ordered = request.getOrdered();
//                Long shippingAddressId = request.getShippingAddressId();
//                Long billingAddressId = request.getBillingAddressId();
//
//
//                if(shippingAddressId != null){
//                ShippingAddress shippingAddress = shippingAddressRepo.findShippingAddressById(id)
//                .orElseThrow(() -> new Exception("Shipping address does not exits"));
////            Address address = addressRepo.findAddressByShippingAddresses(shippingAddress)
////                    .orElseThrow(() -> new Exception("Address does not exists"));
////            UserProfile addressUser = userRepo.findByUsername(principal.getName());
////
////            if(addressUser != address){
////                throw new Exception("This address does not belong to you");
////            }
//
//                orderO.setShippingAddress(shippingAddress);
//                }
//
////        if(billingAddressId != null){
////            BillingAddress billingAddress = billingAddressRepo.findBillingAddressById(id)
////                    .orElseThrow(() -> new Exception("Billing address does not exits"));
////            Address address = addressRepo.findAddressByBillingAddresses(billingAddress)
////                    .orElseThrow(() -> new Exception("Address does not exists"));
////            UserProfile addressUser = userRepo.findByUsername(principal.getName());
////
////            if(addressUser != address){
////                throw new Exception("This address does not belong to you");
////            }
//
////            orderO.setBillingAddress(billingAddress);
////        }
//
//
//                if(ordered){
//                if(orderO.getShippingAddress() == null){
//                throw new Exception("You need to enter shipping address");
//                }
//
//                if(orderO.getBillingAddress() == null){
//                throw new Exception("You need to enter billing address");
//                }
//                orderO.setOrdered(true);
//                }
//
//                orderRepo.save(orderO);

// update

// ordering order
//List<OrderProduct> orderProductList = orderProductRepo.findOrderProductsByOrderO(orderO);
//        for(OrderProduct orderProduct : orderProductList){
//                orderProduct.setTimeUpdated(now());
//                orderProduct.setOrdered(true);
//                orderProduct.setTimeOrdered(now());
//                }
//                orderProductRepo.saveAll(orderProductList);
//
//                orderO.setTimeUpdated(now());
//                orderO.setOrderedTime(now());
//                orderO.setOrdered(true);
//
//                if(orderO.getCoupon() != null){
//                Coupon coupon = orderO.getCoupon();
//                if(!coupon.isApplied()){
//                coupon.setApplied(true);
//                couponRepo.save(coupon);
//                }
//                }
//
//                orderRepo.save(orderO);

// ordering order