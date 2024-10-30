package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.model.Request.AddSideDishToProductRequest;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.model.Request.OrderProductUpdateRequest;
import com.example.foodapp.order.service.customer.AppendicesOrderProductCustomerService;
import com.example.foodapp.order.service.customer.CustomerOrderProductService;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerOrderProductService;
import com.example.foodapp.product.model.Request.SideDishRequest;
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
            @RequestBody OrderProductUpdateRequest orderProductRequest,
            @PathVariable Long id,
            Principal principal
    ){
        try {
            customerOrderProductService.update(id, orderProductRequest, principal);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrderProduct(@RequestBody OrderProductRequest orderProductRequest, Principal principal){
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderProduct(@PathVariable Long id,
                                                     Principal principal
    ){
        try {
            return ResponseEntity.ok()
                    .body(customerOrderProductService.deleteOrderProduct(id, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-side-dish/{id}")
    public ResponseEntity<String> addSideDishToOrderProduct(
            @PathVariable(name = "id") Long orderProductId,
            @RequestBody AddSideDishToProductRequest sideDishToProductRequest,
            Principal principal){
        try {
            return ResponseEntity.ok()
                    .body(appendicesOrderProductCustomerService
                            .addSideDishToOrderProduct(
                                    orderProductId,
                                    sideDishToProductRequest,
                                    principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> initializeOrderProduct(
            @PathVariable Long id,
            Principal principal){
        try {
            return ResponseEntity.ok()
                    .body(appendicesOrderProductCustomerService.initializeOrderProduct(id, principal));
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