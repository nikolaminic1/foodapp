package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Coupon;
import com.example.foodapp.order.service.customer.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/coupon")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerCouponResource {
    private final CustomerCouponService customerCouponService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getCoupon(@PathVariable Long id, Principal principal){

        try {
            Coupon coupon = customerCouponService.get(id, principal);
            return ResponseEntity.ok(Response.builder()
                    .data(Map.of("coupon", coupon))
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .timeStamp(now())
                    .build());

        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    (Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .timeStamp(now())
                            .build())
            );
        }
    }

    @GetMapping("/get_list")
    public ResponseEntity<Response> getCoupons(Principal principal){

        try {
            List<Coupon> couponList = customerCouponService.getList(principal);
            return ResponseEntity.ok(Response.builder()
                    .data(Map.of("couponList", couponList))
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .timeStamp(now())
                    .build());

        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    (Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .timeStamp(now())
                            .build())
            );
        }
    }

    @PostMapping("/apply_to_order/{couponCode}")
    public ResponseEntity<Response> applyCouponToOrder(@PathVariable String couponCode, Principal principal){
        try {
            customerCouponService.applyCouponToOrder(couponCode, principal);

            return ResponseEntity.ok(Response.builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .timeStamp(now())
                    .build());

        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    (Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .timeStamp(now())
                            .build())
            );
        }
    }
}
