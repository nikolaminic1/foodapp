package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.service.business.OwnerProductService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/products/product")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductResourceBusiness {
    private final OwnerProductService ownerProductService;

    @GetMapping("/list")
//    @JsonView(View.Public.class)
    public ResponseEntity<PaginatedResponse<Product>> getProducts(
            @RequestParam Integer page,
            @RequestParam Integer per_page,
            @RequestParam Integer order,
            @RequestParam Integer visible,
            Principal principal
    ){
        try {
            var data = ownerProductService.list(page, per_page, order, visible, principal);
            System.out.println(data);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id ){
        try {
            return ResponseEntity.ok().body(ownerProductService.get(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
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
