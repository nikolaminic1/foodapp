package com.example.foodapp.product.resource.business_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Request.SideDishCreateRequest;
import com.example.foodapp.product.service.business.OwnerSideDishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/side-dish")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class SideDishBusinessResource {
    private final OwnerSideDishService ownerSideDishService;

    @PostMapping("/save")
    public ResponseEntity<Response> createSideDishCategory(@RequestBody SideDishCreateRequest appendicesCreateRequest, Principal principal)
    {
        try {
            SideDish sideDish = ownerSideDishService.create(appendicesCreateRequest, principal);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("Created")
                            .data(Map.of("Appendix", sideDish))
                            .timeStamp(now())
                            .statusCode(HttpStatus.CREATED.value())
                            .status(HttpStatus.CREATED)
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(e.getMessage())
                            .timeStamp(now())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST)
                            .build()
            );
        }
    }

}
