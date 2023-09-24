package com.example.foodapp.business.resource.admin_resource;


import com.example.foodapp.api_resources.Response;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/business/admin")
@RequiredArgsConstructor
public class AdminBusinessResource {
    private final AdminBusinessService adminBusinessService;
    private final UserRepository userRepo;

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getAdminBusiness(@PathVariable("id") Long id) {
        System.out.println(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("single_business", adminBusinessService.get(id)))
                        .message("Single business retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createAdminBusiness(Business business) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                        .data(Map.of("business", adminBusinessService.create(business)))
                        .message("Business created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateAdminBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("business", adminBusinessService.update(id)))
                        .message("Business updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deleteAdminBusiness(@PathVariable("id") Long id) {
        adminBusinessService.delete(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Business status", "deleted ..."))
                        .message("Business deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
