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
@RequestMapping("/api/v1/customer/order_product")
@RequiredArgsConstructor
@Log4j2
public class CustomerOrderProductResource {
    private final CustomerOrderProductService customerOrderProductService;
    private final NoAuthCustomerOrderProductService noAuthCustomerOrderProductService;
    private final AppendicesOrderProductCustomerService appendicesOrderProductCustomerService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveOrderProduct(@RequestBody OrderProductRequest orderProductRequest, Principal principal){

        if(principal != null){
            try{
                customerOrderProductService.create(orderProductRequest, principal);

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
        } else {
            try {
                noAuthCustomerOrderProductService.create(orderProductRequest);

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
