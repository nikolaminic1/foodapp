package com.example.foodapp.product.resource.admin_resource;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.service.business.OwnerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/admin/product/product_model")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductResourceAdmin {
    private final OwnerProductService ownerProductService;

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponse<Product>> getProducts(Principal principal){
        try {
            return ResponseEntity.ok().body(ownerProductService.list(1, 1, 1, 1, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("single_product", ownerProductService.get(id)))
                        .message("Single product retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createProducts(@RequestBody Product product) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                        .data(Map.of("products", ownerProductService.create(product)))
                        .message("Product created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Product updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("products", ownerProductService.delete(id)))
                        .message("Product deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

}
