package com.example.foodapp.business.resource.owner_resource;

import com.example.foodapp.business.model.BusinessLocation;
import com.example.foodapp.business.model.Requests.AddressUpdateRequest;
import com.example.foodapp.business.service.owner_service.BusinessLocationBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/business/address")
@RequiredArgsConstructor
@Log4j2
public class BusinessAddressResource {
    private final BusinessLocationBusinessService businessService;

    @GetMapping
    public ResponseEntity<BusinessLocation> getAddress(Principal principal){
        try {
            return ResponseEntity.ok().body(businessService.get(principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> updateAddress(
            @RequestBody AddressUpdateRequest request,
            Principal principal) {
        try {
            System.out.println(request);
            return ResponseEntity.ok().body(businessService.update(request, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
