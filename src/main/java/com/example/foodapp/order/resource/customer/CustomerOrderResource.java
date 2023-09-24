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
    public ResponseEntity<Response> getMyOrder(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(
                Response.builder()
                .message("order")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .timeStamp(now())
                .build()
        );
    }

    @GetMapping("/get_active_order")
    public ResponseEntity<Response> getMyOrder(Principal principal) {
        try {
            OrderO orderO = customerOrderService.getActiveOrder(principal);
            return ResponseEntity.ok(
                    Response.builder()
                            .message("active order")
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .data(Map.of("order", orderO))
                            .timeStamp(now())
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(e.getMessage())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST)
                            .timeStamp(now())
                            .build());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Response> updateOrder(@PathVariable Long id,
                                                @RequestBody OrderCustomerUpdateRequest orderUpdateRequest,
                                                Principal principal){
        try {
            customerOrderService.update(id, orderUpdateRequest, principal);
            return ResponseEntity.ok().body(
                    Response.builder()
                            .message("Order updated")
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .timeStamp(now())
                            .build());

        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(e.getMessage())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST)
                            .timeStamp(now())
                            .build());
        }

    }

}
