package com.example.foodapp.order.service.customer.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.UserProfileRepo;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.order.exception.APIConnectionException;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Payment;
import com.example.foodapp.order.model.Request.PaymentRequest;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.repo.PaymentRepo;
import com.example.foodapp.order.service.customer.CustomerPaymentService;
import com.stripe.exception.*;
//import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.stripe.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerPaymentServiceImplementation implements CustomerPaymentService {
    private final PaymentRepo paymentRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderRepo orderRepo;
    private final UserProfileRepo userProfileRepo;

    @Override
    public Payment create(@RequestBody PaymentRequest paymentRequest, Principal principal) throws Exception {
        UserProfile userProfile = userRepo.findByEmail(principal.getName()).orElseThrow().getUserProfile();
        String stripeToken = paymentRequest.getStripeToken();
        String paymentMethodId = paymentRequest.getPaymentMethodId();
        Long orderId = paymentRequest.getOrderId();
        String stripeCustomerId = userProfile.getStripeId();
        Customer customer;

        if (stripeCustomerId == null) {
            String customerId = createCustomer(stripeToken, userProfile.getEmail());
            userProfile.setStripeId(customerId);
            userProfile.setOneClickPurchasing(true);
            userProfileRepo.save(userProfile);
            customer = retrieveCustomer(customerId);
        } else {
            customer = retrieveCustomer(stripeCustomerId);
        }

//        UserProfiles.Customer customerObj = userProfileService.returnCustomer(userProfile);
//        boolean doesOrderExists = orderRepo.findById(orderId).isPresent();
//        boolean isActiveOrderPresent = orderRepo.findOrderOByCustomerAndOrdered(customerObj, false).isPresent();

//        if(doesOrderExists){
//            throw new Exception("This order does not exists");
//        }
//
//        if(isActiveOrderPresent){
//            throw new Exception("You currently do not have an active order");
//        }

        OrderO orderFromRequest  = orderRepo.findById(orderId).get();
//        OrderO activeOrder = orderRepo.findOrderOByCustomerAndOrdered(customerObj, false).get();

//        if(orderFromRequest != activeOrder){
//            throw new Exception("This order does not belong to you");
//        }

        Payment payment = new Payment();
//        payment.setOrderO(activeOrder);
//        payment.setAmount(activeOrder.getPrice());
        payment.setIsCharged(false);
        payment.setDateCreated(now());

        // charge payment
//        charge(payment, customer, principal, paymentMethodId);

        paymentRepo.save(payment);
//        orderRepo.save(activeOrder);

        return payment;
    }

    @Override
    public Payment get(Long id) {
        return null;
    }

    @Override
    public Payment update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void charge(Payment payment, com.example.foodapp.auth.user.UserProfiles.Customer customer, Principal principal, String paymentMethodId) throws Exception {

    }

//    @Override
//    public void charge(Payment payment, Customer customer, Principal principal, String paymentMethodId) throws Exception {
//        List<Object> paymentMethodTypes =
//                new ArrayList<>();
//
//        paymentMethodTypes.add("card");
//
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("customer", customer.getId());
//        params.put("amount", payment.getAmount());
//        params.put("currency", "usd");
//        params.put("metadata", payment.getOrderO().getUuid());
//        params.put(
//                "payment_method_types",
//                paymentMethodTypes
//        );
//
//        params.put("confirm", true);
//
//        try {
//            PaymentIntent paymentIntent =
//                    PaymentIntent.create(params);
//
//            // confirm payment
//
//            // Use Stripe's library to make requests...
//        } catch (CardException e) {
//            // Since it's a decline, CardException will be caught
//            System.out.println("Status is: " + e.getCode());
//            System.out.println("Message is: " + e.getMessage());
//            throw e;
//        } catch (RateLimitException e) {
//            // Too many requests made to the API too quickly
//            throw e;
//        } catch (InvalidRequestException e) {
//            // Invalid parameters were supplied to Stripe's API
//            System.out.println("InvalidRequestException");
//            throw e;
//        } catch (AuthenticationException e) {
//            // Authentication with Stripe's API failed
//            // (maybe you changed API keys recently)
//            System.out.println("AuthenticationException");
//            throw e;
//        } catch (APIConnectionException e) {
//            // Network communication with Stripe failed
//            System.out.println("APIConnectionException");
//            throw e;
//        } catch (StripeException e) {
//            // Display a very generic error to the user, and maybe send
//            // yourself an email
//            System.out.println("StripeException");
//            throw e;
//        } catch (Exception e) {
//            // Something else happened, completely unrelated to Stripe
//            System.out.println("Exception");
//            throw e;
//        }
//
//    }


    @Override
    public String createCustomer(String token, String email) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("token", token);
        Customer customer = Customer.create(params);
        return customer.getId();
    }

    @Override
    public Customer retrieveCustomer(String id) throws Exception{
        try {
            return Customer.retrieve(id);
            // Use Stripe's library to make requests...
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
            throw e;
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            throw e;
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
            System.out.println("InvalidRequestException");
            throw e;
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed
            // (maybe you changed API keys recently)
            System.out.println("AuthenticationException");
            throw e;
        } catch (APIConnectionException e) {
            // Network communication with Stripe failed
            System.out.println("APIConnectionException");
            throw e;
        } catch (StripeException e) {
            // Display a very generic error to the user, and maybe send
            // yourself an email
            System.out.println("StripeException");
            throw e;
        } catch (Exception e) {
            // Something else happened, completely unrelated to Stripe
            System.out.println("Exception");
            throw e;
        }
    }
}
