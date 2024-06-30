package com.example.foodapp.product.resource.customer_resource;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.service.business.OwnerProductService;
import com.example.foodapp.product.service.cutomer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/customer/product/product_model")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class ProductResourceCustomer {
    private final CustomerProductService customerProductService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long id, Principal principal){
        try {
            return ResponseEntity.ok().body(customerProductService.get(id, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


//    @GetMapping("/list")
//    public ResponseEntity<?> getProducts(Principal principal){
//        try {
//            return ResponseEntity.ok().body(customerProductService.list("1", "1", "1", "1", principal));
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

}
