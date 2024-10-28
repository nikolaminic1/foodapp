package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.service.customer.AppendicesOrderProductCustomerService;
import com.example.foodapp.order.service.customer.CustomerOrderProductService;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerOrderProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer/order/order-product")
@RequiredArgsConstructor
@Log4j2
public class CustomerOrderProductResource {
    private final CustomerOrderProductService customerOrderProductService;
    private final NoAuthCustomerOrderProductService noAuthCustomerOrderProductService;
    private final AppendicesOrderProductCustomerService appendicesOrderProductCustomerService;

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateOrderProduct(
            @RequestBody OrderProductRequest orderProductRequest,
            @PathVariable String id,
            Principal principal
    ){
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrderProduct(@RequestBody OrderProductRequest orderProductRequest, Principal principal){
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> initializeOrderProduct(
            @PathVariable Long id,
            @RequestParam(value = "businessId", defaultValue = "0") Long businessId,
            Principal principal){
        try {
            return ResponseEntity.ok()
                    .body(appendicesOrderProductCustomerService.initializeOrderProduct(id, businessId, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_appendix")
    public ResponseEntity<Response> addAppendixToOrderProduct(@RequestBody AddAppendixRequest addAppendixRequest, Principal principal){
        try {
            appendicesOrderProductCustomerService.addToOrderProduct(addAppendixRequest, principal);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("OK message")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );

        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
    }
}


//        if(principal != null){
//                try{
//                customerOrderProductService.create(orderProductRequest, principal);
//
//                return ResponseEntity.ok(
//                Response.builder()
//                .message("OK message")
//                .status(HttpStatus.OK)
//                .statusCode(HttpStatus.OK.value())
//                .build()
//                );
//                } catch (Exception e){
//                return ResponseEntity.badRequest().body(
//                Response.builder()
//                .message(e.getMessage())
//                .status(HttpStatus.BAD_REQUEST)
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .build()
//                );
//                }
//                } else {
//                try {
//                noAuthCustomerOrderProductService.create(orderProductRequest);
//
//                return ResponseEntity.ok(
//                Response.builder()
//                .message("OK message")
//                .status(HttpStatus.OK)
//                .statusCode(HttpStatus.OK.value())
//                .build()
//                );
//
//                } catch (Exception e){
//                return ResponseEntity.badRequest().body(
//                Response.builder()
//                .message(e.getMessage())
//                .status(HttpStatus.BAD_REQUEST)
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .build()
//                );
//                }
//                }