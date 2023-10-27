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
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/business/restaurant")
@RequiredArgsConstructor
@Log4j2
public class OwnerBusinessResource {
    private final OwnerBusinessService businessService;

    @GetMapping
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
            @RequestBody  Map<String, Object> payload, Principal principal
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

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Response> updateOwnerBusiness(@PathVariable("id") Long id, @RequestBody BusinessUpdateRequest businessUpdateRequest, Principal principal) {

        if(businessService.update(id, businessUpdateRequest, principal)==null){
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(now())
                    .data(Map.of("business", "Bad request"))
                    .message("Business updated")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("business", businessService.update(id, businessUpdateRequest, principal)))
                            .message("Business updated")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteOwnerBusiness(@PathVariable("id") Long id) {
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
