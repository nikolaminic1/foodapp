package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.service.business.OwnerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/product_detail")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductResourceBusiness {
    private final OwnerProductService ownerProductService;

    @GetMapping("/list")
    public ResponseEntity<Response> getProducts(){
        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(Map.of("products", ownerProductService.list(30)))
                .message("List of products")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Response> getProducts(@PathVariable("id") Long id ){
        if(ownerProductService.get(id) == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("products", ownerProductService.get(id)))
                            .message("Single product")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createProduct(@RequestBody ProductRequest productRequest, Principal principal){

        if(ownerProductService.create(productRequest, principal) != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("List of products")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("List of products")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }


    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateProduct(@RequestBody ProductRequest productRequest,
                                                  @PathVariable Long id,
                                                  Principal principal){

        ownerProductService.update(productRequest,id , principal);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("List of products")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
