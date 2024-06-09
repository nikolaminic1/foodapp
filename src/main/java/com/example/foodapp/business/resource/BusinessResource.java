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
    public ResponseEntity<?> getBusinesses() {
        return ResponseEntity.ok().body("OK");    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/save")
    public ResponseEntity<?> createBusiness(Business business) {
        return ResponseEntity.ok().body("OK");
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body("OK");
    }
}
