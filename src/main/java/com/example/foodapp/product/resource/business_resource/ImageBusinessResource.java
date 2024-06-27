package com.example.foodapp.product.resource.business_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.product.service.business.BusinessImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/business/product/image")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('ROLE_BUSINESS')")
public class ImageBusinessResource {
    private final BusinessImageService businessImageService;

    @PostMapping("/save")
    public ResponseEntity<Response> productImageUpload(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("productId") Long id,
                                                       Principal principal) throws IOException {

        return null;
//        if(businessImageService.create(file,id, principal) != null){
//            return ResponseEntity.ok(
//                    Response.builder()
//                            .timeStamp(now())
//                            .message("Image uploaded...")
//                            .status(HttpStatus.CREATED)
//                            .statusCode(HttpStatus.CREATED.value())
//                            .build()
//            );
//        } else {
//            return ResponseEntity.badRequest().body(
//                    Response.builder()
//                            .timeStamp(now())
//                            .message("Bad request...")
//                            .status(HttpStatus.BAD_REQUEST)
//                            .statusCode(HttpStatus.BAD_REQUEST.value())
//                            .build()
//            );
//        }
    }
}
