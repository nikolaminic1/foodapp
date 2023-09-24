package com.example.foodapp.order.resource.customer;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Request.PaymentRequest;
import com.example.foodapp.order.service.customer.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/payment")
@RequiredArgsConstructor
@Log4j2
public class CustomerPaymentResource {
    private final CustomerPaymentService customerPaymentService;

    @PostMapping("/charge")
    public ResponseEntity<Response> chargeForOrder(@RequestParam PaymentRequest paymentRequest, Principal principal){
        try {
            customerPaymentService.create(paymentRequest, principal);
            return ResponseEntity.ok()
                    .body(Response.builder()
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .timeStamp(now())
                            .message("OK")
                            .build()
                    );
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST)
                    .timeStamp(now())
                    .message("Bad request")
                    .build()
                    );
        }
    }
}
