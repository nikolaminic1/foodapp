package com.example.foodapp.product.resource.admin_resource;

import com.example.foodapp.product.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/admin/product/initial")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ProductInitialResourceAdmin {
    private final AdminProductService adminProductService;

    @GetMapping
    public ResponseEntity<?> getProductInitialData(@RequestBody Long businessId, Principal principal) {
        try {
            return ResponseEntity.ok().body(adminProductService.getInitial(businessId, principal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

