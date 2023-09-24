package com.example.foodapp.business.resource.admin_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestReview;
import com.example.foodapp.business.service.admin_service.AdminBusinessRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/business_request/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Log4j2
public class AdminBusinessRequestResource {
    private final AdminBusinessRequestService adminBusinessRequestService;

    @GetMapping("/list")
    public ResponseEntity<Response> getAdminBusinessRequestList() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("business requests", adminBusinessRequestService.list(0, 100)))
                        .message("BusinessRequest retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getAdminBusinessRequest(@PathVariable("id") Long id) {
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

    @PatchMapping("/request_review/{id}")
    public ResponseEntity<Response> reviewBusinessRequest(@PathVariable("id") Long id,
                                                          @RequestBody BusinessRequestReview businessRequestReview)
    {
        BusinessRequest businessRequest = adminBusinessRequestService.update(id, businessRequestReview);

        if(businessRequest == null){
            return ResponseEntity.badRequest().body(Response.builder()
                    .timeStamp(now())
                    .data(Map.of("business", "Bad request"))
                    .message("BusinessRequest granted")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build());
        }

        return ResponseEntity.ok().body(Response.builder()
                .timeStamp(now())
                .data(Map.of("business", "Request granted"))
                .message("BusinessRequest granted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createAdminBusinessRequest(BusinessRequest businessRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("business", adminBusinessRequestService.create(businessRequest)))
                        .message("BusinessRequest created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteAdminBusinessRequest(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("business", adminBusinessRequestService.delete(id)))
                        .message("BusinessRequest deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
