package com.example.foodapp.order.resource.noAuthCustomer;



import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.OrderO;
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
@RequestMapping("/api/v1/unauthorized/customer/order")
@RequiredArgsConstructor
@Log4j2
public class NoAuthCustomerOrderResource {
    private final CustomerOrderService customerOrderService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMyOrder(@PathVariable Long id, Principal principal){
        try {
            return ResponseEntity.ok(customerOrderService.getOrder(id, principal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-active-order")
    public ResponseEntity<?> getMyOrder(Principal principal) {
        try {
            String orderO = customerOrderService.getActiveOrder(principal);
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
    public ResponseEntity<?> updateOrder(@PathVariable Long id, Principal principal){
        return ResponseEntity.badRequest().body(
                Response.builder()
                        .message("Order updated")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .timeStamp(now())
                        .build());
    }

}
