package com.example.foodapp.business.resource.admin_resource;


import com.example.foodapp.api_resources.ImageType;
import com.example.foodapp.api_resources.Response;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/business")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@Log4j2
public class AdminBusinessResource {
    private final AdminBusinessService adminBusinessService;
    private final UserRepository userRepo;

    @GetMapping
    public ResponseEntity<?> getAdminListBusiness(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page ,
                                                  @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                                  Principal principal) {
        try {
            if (page == null) {
                page = 1;
            }
            if (limit == null) {
                limit = 20;
            }
            var m = adminBusinessService.list(page, limit, principal);
            System.out.println(m);
            return ResponseEntity.ok().body(m);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminBusiness(@PathVariable("id") Long id, Principal principal) {
        try {
            return ResponseEntity.ok().body(adminBusinessService.get(id, principal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/image")
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> addImage(@RequestParam MultipartFile file,
                                      @RequestParam String type,
                                      @RequestParam Long businessID,
                                      Principal principal) {
        try {
            Boolean status = false;
            if (Objects.equals(type, "BUSINESS_LOGO_IMAGE")){
                status = adminBusinessService.addImage(
                        file,
                        ImageType.BUSINESS_LOGO_IMAGE,
                        businessID,
                        principal
                );
            }

            if (status) {
                return ResponseEntity.ok().body("OK");
            } else {
                return ResponseEntity.badRequest().body("File is not saved.");
            }

        } catch (MaxUploadSizeExceededException exceededException) {
            return ResponseEntity
                    .status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body("File size exceeds limit! Please upload a file smaller than the configured limit.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("File is not saved.");
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> createOrUpdateAdminBusiness(
            @PathVariable Long id,
            @RequestBody BusinessUpdateRequest request,
            Principal principal) {
        try {
            return ResponseEntity.ok().body(adminBusinessService.createOrUpdate(id, request, principal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdminBusiness(@PathVariable("id") Long id, Principal principal) {
        try {
            return ResponseEntity.ok().body(adminBusinessService.delete(id, principal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
