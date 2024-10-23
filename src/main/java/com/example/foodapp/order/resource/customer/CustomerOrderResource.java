package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Request.OrderCustomerUpdateRequest;
import com.example.foodapp.order.service.customer.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/order")
@RequiredArgsConstructor
@Log4j2
public class CustomerOrderResource {
    private final CustomerOrderService customerOrderService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMyOrder(@PathVariable Long id, Principal principal){
        try {
            return ResponseEntity.ok().body(customerOrderService.getOrder(id, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-active-order")
    public ResponseEntity<?> getActiveOrder(Principal principal) {
        try {
            return ResponseEntity.ok().body(customerOrderService.getActiveOrder(principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id,
                                                @RequestBody OrderCustomerUpdateRequest orderUpdateRequest,
                                                Principal principal){
        try {
            return ResponseEntity.ok().body(customerOrderService.update(id, orderUpdateRequest, principal));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
