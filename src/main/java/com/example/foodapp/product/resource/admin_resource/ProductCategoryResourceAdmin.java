package com.example.foodapp.product.resource.admin_resource;

import com.example.foodapp.product.service.business.OwnerProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/product/product_categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductCategoryResourceAdmin {
    private final OwnerProductCategoryService ownerProductCategoryService;


}
