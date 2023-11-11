package com.example.foodapp.business.resource.owner_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1/owner/business/restaurant")
@RequiredArgsConstructor
@Log4j2
public class OwnerBusinessResource {
    private final OwnerBusinessService businessService;

    @GetMapping("/get")
    @JsonView(View.Internal.class)
    public ResponseEntity<Business> getOwnerBusiness(Principal principal) {
        System.out.println(principal);
        try {
            return ResponseEntity.ok().body(businessService.get(principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createOwnerBusiness(
            @RequestBody Map<String, Object> payload, Principal principal
    ) {

        String name = (String) payload.get("name");
        String desc = (String) payload.get("description");

//        if(name == null || desc == null || principal == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } else {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                            .data(Map.of("business", businessService.create(name, desc, principal)))
                        .message("Business created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
//        }
    }


    @PostMapping("/update")
    public ResponseEntity<Business> updateOwnerBusiness(@RequestBody BusinessUpdateRequest businessUpdateRequest, Principal principal) {

        try {
            return ResponseEntity.ok().body(businessService.update(businessUpdateRequest, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/background-image")
    public ResponseEntity<String> updateBackgroundImage(
            @RequestParam("image") MultipartFile file,
            Principal principal) {
        try {
            return ResponseEntity.ok().body(businessService.uploadBackgroundImage(file, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/logo-image")
    public ResponseEntity<String> updateLogoImage(@RequestParam("image") MultipartFile file,
                                                    Principal principal) {
        try {
            return ResponseEntity.ok().body(businessService.uploadLogoImage(file, principal));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/background-image")
    public ResponseEntity<String> deleteBackgroundImage(Principal principal) {
        try {
            businessService.deleteBackgroundImage(principal);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/logo-image")
    public ResponseEntity<String> deleteLogoImage(Principal principal) {
        try {
            businessService.deleteLogoImage(principal);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOwnerBusiness(@PathVariable("id") Long id) {
        try {
//            businessService.delete(id);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}