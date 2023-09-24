package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.ProductVariation;
import com.example.foodapp.product.model.Request.ProductVariationRequest;
import com.example.foodapp.product.service.business.OwnerProductVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/product_variation")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductVariationResourceBusiness {
    private final OwnerProductVariationService ownerProductVariationService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveProductVariation(@RequestBody ProductVariationRequest productVariationRequest,
                                                         Principal principal)
    {
        ProductVariation productVariation = ownerProductVariationService.create(productVariationRequest, principal);
        if (productVariation != null){
            return ResponseEntity.ok(
                    Response.builder().message("Created product variation")
                    .statusCode(HttpStatus.OK.value())
                    .status(HttpStatus.OK)
                    .timeStamp(now())
                    .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder().message("Bad request")
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST)
                            .timeStamp(now())
                            .build()
            );
        }
    }
}
