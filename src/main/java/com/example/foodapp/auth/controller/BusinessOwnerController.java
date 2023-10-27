package com.example.foodapp.auth.controller;

import com.example.foodapp.auth.dto.BusinessOwnerUpdateRequest;
import com.example.foodapp.auth.service.BusinessOwnerBusinessService;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/business/business-owner")
@RequiredArgsConstructor
@Log4j2
public class BusinessOwnerController {
    private final BusinessOwnerBusinessService businessOwnerBusinessService;

    @GetMapping
    public ResponseEntity<BusinessOwner> getBusinessOwnerDetail(Principal principal) {
        try {
            return ResponseEntity.ok().body(businessOwnerBusinessService.get(principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> updateBusinessOwnerDetail(@RequestBody BusinessOwnerUpdateRequest request, Principal principal) {
        try {
            businessOwnerBusinessService.update(principal, request);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
