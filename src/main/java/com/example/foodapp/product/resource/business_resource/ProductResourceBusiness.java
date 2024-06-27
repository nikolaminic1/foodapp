package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ChangeCategoryRequest;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.service.business.OwnerProductService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/product_model")
@RequiredArgsConstructor
@Log4j2
//@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductResourceBusiness {
    private final OwnerProductService ownerProductService;

    @GetMapping("/list")
    public ResponseEntity<?> getProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") String page,
            @RequestParam(name = "limit", required = false, defaultValue = "20") String limit,
            @RequestParam(name = "order", required = false, defaultValue = "3") String order,
            @RequestParam(name = "visible", required = false, defaultValue = "0") String visible,
            Principal principal
    ){
        try {
            var data = ownerProductService.list(page, limit, order, visible, principal);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list-delete/{id}")
    public ResponseEntity<?> getProductsToDelete(
            @PathVariable Long id,
            Principal principal
    ){
        try {
            var data = ownerProductService.getProductsToDelete(principal, id);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list-options")
    public ResponseEntity<?> getProductCategoryListOptions(
            Principal principal
    ){
        try {
            var data = ownerProductService.getListOptions(principal);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(@PathVariable("id") Long id, Principal principal){
        try {
            return ResponseEntity.ok().body(ownerProductService.get(principal, id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/change-category")
    public ResponseEntity<String> changeProductCategory(@RequestBody ChangeCategoryRequest request, Principal principal){
        try {
            return ResponseEntity.ok().body(ownerProductService.changeProductCategory(request, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, Principal principal){
        try {
            return ResponseEntity.ok().body(ownerProductService.delete(id, principal));
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

        try {
            ownerProductService.update(productRequest,id , principal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
