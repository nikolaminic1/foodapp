package com.example.foodapp.auth.controller.admin;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.service.admin.service.AdminAddressService;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/address")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Log4j2
public class AdminAddressController {
    private final AdminAddressService adminAddressService;

    @GetMapping
    public ResponseEntity<Object> get (@RequestBody Long id, Principal principal) {
        try {
            AddressModel address = adminAddressService.get(id, principal);
            return ResponseEntity.ok().body(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll (Principal principal) {
        try {
            List<AddressModel> address = adminAddressService.getAll(principal);
            System.out.println(address);
            return ResponseEntity.ok().body(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> update (AddressRequest request, Principal principal) {
        try {
            AddressModel address = adminAddressService.update(request, principal);
            return ResponseEntity.ok().body(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete (Long id, Principal principal) {
        try {
            adminAddressService.delete(id, principal);
            return ResponseEntity.ok().body("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
