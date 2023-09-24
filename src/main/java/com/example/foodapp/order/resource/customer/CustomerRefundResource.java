package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Refund;
import com.example.foodapp.order.model.Request.RefundRequest;
import com.example.foodapp.order.service.customer.CustomerRefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Ref;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/refund")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerRefundResource {
    private final CustomerRefundService customerRefundService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getRefund(@PathVariable Long id, Principal principal){
        try {
            Refund refund =customerRefundService.get(id, principal);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("OK")
                            .data(Map.of("refund", refund))
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .timeStamp(now())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .timeStamp(now())
                                    .build()
                    );
        }
    }

    @GetMapping("/get_list")
    public ResponseEntity<Response> getRefundsList(Principal principal) {
        try {
            List<Refund> refundList = customerRefundService.list(principal);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("OK")
                            .data(Map.of("refunds", refundList))
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .timeStamp(now())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .timeStamp(now())
                                    .build()
                    );
        }
    }

    @PostMapping("/request_refund")
    public ResponseEntity<Response> requestRefund(@RequestBody RefundRequest refundRequest, Principal principal){
        try {
            Refund refund = customerRefundService.create(refundRequest, principal);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("OK")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .timeStamp(now())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .timeStamp(now())
                                    .build()
                    );
        }
    }
}
