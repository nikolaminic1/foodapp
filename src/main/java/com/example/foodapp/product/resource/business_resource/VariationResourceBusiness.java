package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Request.VariationRequest;
import com.example.foodapp.product.service.business.OwnerVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/business/product/variation")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class VariationResourceBusiness {
    private final OwnerVariationService ownerVariationService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveVariation(@RequestBody VariationRequest variationRequest,
                                                  Principal principal){

        if(ownerVariationService.create(variationRequest, principal) == null){
            return ResponseEntity.badRequest()
                    .body(Response.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("Bad request...")
                            .build());
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Variation created...")
                            .build()
            );
        }

    }
}
