package com.example.foodapp.business.resource.owner_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestRequest;
import com.example.foodapp.business.service.admin_service.AdminBusinessRequestService;
import com.example.foodapp.business.service.owner_service.OwnerBusinessRequestService;
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
@RequestMapping("/api/v1/business_request/owner")
@RequiredArgsConstructor
public class OwnerBusinessRequestResource {
    private final OwnerBusinessRequestService ownerBusinessRequestService;
    private final AdminBusinessRequestService adminBusinessRequestService;
    private final UserRepository userRepo;

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    public ResponseEntity<Response> getOwnerBusinessRequest(@PathVariable("id") Long id) {
        System.out.println(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("single_business", adminBusinessRequestService.get(id)))
                        .message("BusinessRequest retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> createOwnerBusinessRequest(@RequestBody BusinessRequestRequest payload, Principal principal)
    {
        BusinessRequest businessRequest = ownerBusinessRequestService.create(payload, principal);
        if(businessRequest != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
//                        .data(Map.of("single_business", adminBusinessRequestService.update(businessRequest)))
                            .message("Create business request by owner")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
//                        .data(Map.of("single_business", adminBusinessRequestService.update(businessRequest)))
                            .message("Business request already exits...")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateOwnerBusinessRequest(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
//                        .data(Map.of("business", adminBusinessRequestService.create(businessRequest)))
                        .message("BusinessRequest created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteOwnerBusinessRequest(@PathVariable("id") Long id, Principal principal) {
        ownerBusinessRequestService.delete(id, principal);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("BusinessRequest deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
