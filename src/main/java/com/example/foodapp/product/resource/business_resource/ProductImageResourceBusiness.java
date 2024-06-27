package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.product.service.business.BusinessImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/business/product/product_image")
@RequiredArgsConstructor
@PreAuthorize("hasRole('BUSINESS')")
public class ProductImageResourceBusiness {
    private final BusinessImageService businessImageService;

    @PostMapping("/{id}")
    public ResponseEntity<String> updateProductImage(
            @RequestParam("image") MultipartFile file,
            @PathVariable Long id,
            Principal principal) {
        try {
            return ResponseEntity.ok().body(businessImageService.create(file, id, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
