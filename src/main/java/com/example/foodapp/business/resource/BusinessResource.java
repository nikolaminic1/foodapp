package com.example.foodapp.business.resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
public class BusinessResource {
    private final AdminBusinessService businessService;

    @GetMapping("/list")
    public ResponseEntity<Response> getBusinesses() {
        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(Map.of("business", businessService.list(0, 100)))
                .message("Business retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getBusiness(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                .timeStamp(now())
                .data(Map.of("single_business", businessService.get(id)))
                .message("Single business retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createBusiness(Business business) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                        .data(Map.of("business", businessService.create(business)))
                        .message("Business created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                        .data(Map.of("business", businessService.update(id)))
                        .message("Business updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("business", businessService.delete(id)))
                        .message("Business deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
