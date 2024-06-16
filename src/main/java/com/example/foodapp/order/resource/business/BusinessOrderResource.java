package com.example.foodapp.order.resource.business;

import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import com.example.foodapp.order.service.business.BusinessOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/business/orders/order")
@RequiredArgsConstructor
public class BusinessOrderResource {
    private final BusinessOrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerBusiness(@RequestParam Long id, Principal principal) {
        try {
            return ResponseEntity.ok().body(service.get(id, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getOwnerBusiness(Principal principal) {
        try {
            return ResponseEntity.ok().body(service.list(principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
