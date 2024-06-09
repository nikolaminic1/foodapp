package com.example.foodapp.product.resource.admin_resource;

import com.example.foodapp.product.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/admin/product/initial")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ProductInitialResourceAdmin {
    private final AdminProductService adminProductService;

    @GetMapping
    public ResponseEntity<?> getProductInitialData() {
        try {
            return ResponseEntity.ok().body(adminProductService.getInitial());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

