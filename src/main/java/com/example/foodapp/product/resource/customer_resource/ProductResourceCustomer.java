package com.example.foodapp.product.resource.customer_resource;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.service.business.OwnerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/customer/product/product_detail")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class ProductResourceCustomer {
    private final OwnerProductService ownerProductService;

    @GetMapping("/list")
    public ResponseEntity<?> getProducts(Principal principal){
        try {
            return ResponseEntity.ok().body(ownerProductService.list("1", "1", "1", "1", principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
