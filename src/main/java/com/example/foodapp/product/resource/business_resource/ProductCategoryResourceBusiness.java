package com.example.foodapp.product.resource.business_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;
import com.example.foodapp.product.service.business.OwnerProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/product_categories")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ProductCategoryResourceBusiness {
    private final OwnerProductCategoryService productCategoryService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveProductCategories(@RequestBody ProductCategoryRequest payload,
                                                          Principal principal)
    {
        log.info("saving product category");

        if(principal == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Response.builder()
                            .timeStamp(now())
                            .message("Unauthorized...")
                            .status(HttpStatus.UNAUTHORIZED)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .build()
            );
        }
        ProductCategory productCategory = productCategoryService.create(payload, principal);

        if(productCategory != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("Product category created...")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(now())
                            .message("Product category is not created, bad request...")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
    }

    @GetMapping("/get_my_restaurant_categories")
    public ResponseEntity<Response> getMyRestaurantCategories(Principal principal)
    {

        List<ProductCategory> productCategoryList = productCategoryService.getMyList(principal);

        if(productCategoryList != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("Categories retrieved ...")
                            .data(Map.of("productCategories", productCategoryList))
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(now())
                            .message("Categories retrieved ...")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }

    }

    @DeleteMapping("/delete_business_category/{id}")
    public ResponseEntity<Response> deleteRestaurantCategory(@PathVariable Long id, Principal principal)
    {
        productCategoryService.delete(id, principal);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Delete business category ...")
                        .data(Map.of("restaurant", ""))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
