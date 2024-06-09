package com.example.foodapp.product.resource.admin_resource;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.service.admin.AdminProductService;
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
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ProductResourceAdmin {
    private final AdminProductService adminProductService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            Principal principal)
    {
        try {
            var products = adminProductService.list(page, limit, principal);
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(adminProductService.get(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
