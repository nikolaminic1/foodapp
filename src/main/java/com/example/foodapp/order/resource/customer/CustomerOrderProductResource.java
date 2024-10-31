package com.example.foodapp.order.resource.customer;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.model.Request.AddSideDishToProductRequest;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.model.Request.OrderProductUpdateRequest;
import com.example.foodapp.order.service.customer.SideDishOrderProductCustomerService;
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
    private final SideDishOrderProductCustomerService appendicesOrderProductCustomerService;

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

    @PostMapping("/add-to-cart/{id}")
    public ResponseEntity<String> saveOrderProduct(@PathVariable Long id, Principal principal){
        try {
            return ResponseEntity.ok().body(customerOrderProductService.addToOrder(id, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/update-quantity/{orderProductId}")
    public ResponseEntity<String> updateQuantity(@PathVariable Long orderProductId,
                                                 @RequestBody Integer quantity,
                                                 Principal principal){
        try {
            return ResponseEntity.ok().body(customerOrderProductService
                    .updateQuantity(orderProductId, quantity, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderProduct(@PathVariable Long id,
                                                     Principal principal
    ){
        try {
            return ResponseEntity.ok()
                    .body(customerOrderProductService.get(id, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
    public ResponseEntity<String> updateSideDishToOrderProduct(
            @PathVariable(name = "id") Long orderProductId,
            @RequestBody Long sideDishId,
            Principal principal){
        try {
            return ResponseEntity.ok()
                    .body(customerOrderProductService
                            .addSideDishToOrderProduct(
                                    orderProductId,
                                    sideDishId,
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