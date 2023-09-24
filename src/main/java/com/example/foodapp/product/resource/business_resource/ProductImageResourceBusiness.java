package com.example.foodapp.product.resource.business_resource;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/business/product/product_image")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductImageResourceBusiness {
}
