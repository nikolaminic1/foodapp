package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Request.ProductTagRequest;
import com.example.foodapp.product.service.business.OwnerProductTagService;
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
@RequestMapping("/api/v1/business/product/product_tag")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductTagResourceBusiness {
    private final OwnerProductTagService ownerProductTagService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveProductTag(@RequestBody ProductTagRequest productTagRequest, Principal principal)
    {

        if(ownerProductTagService.create(productTagRequest, principal) != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .message("Product tags created...")
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .build()
            );
        }

        return ResponseEntity.ok(
                Response.builder()
                        .message("Bad request...")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );


    }
}
