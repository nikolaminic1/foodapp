package com.example.foodapp.auth.controller.customer;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.service.customer.service.CustomerAddressService;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/customer/address")
@RequiredArgsConstructor
@Log4j2
public class AddressController {
    private final CustomerAddressService customerAddressService;


    @GetMapping("/get")
    public ResponseEntity<Object> get (
            @RequestBody Long id, Principal principal
    ) {
        try {
            AddressModel address = customerAddressService.get(id, principal);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createOrUpdate (
            @RequestBody AddressRequest request, Principal principal
    ) {
        try {
            AddressModel address = customerAddressService.createOrUpdate(request, principal);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping
    public ResponseEntity<Object> delete (
            @RequestBody Long id, Principal principal
    ) {
        try {
            customerAddressService.delete(id, principal);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
