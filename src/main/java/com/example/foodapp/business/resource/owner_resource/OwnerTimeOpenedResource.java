package com.example.foodapp.business.resource.owner_resource;

import com.example.foodapp.api_resources.Response;
import com.example.foodapp.business.service.owner_service.OwnerTimeOpenedDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/time_opened/owner")
@RequiredArgsConstructor
@Log4j2
public class OwnerTimeOpenedResource {
    private final OwnerTimeOpenedDayService ownerTimeOpenedDayService;

    @PostMapping("/save")
    public ResponseEntity<Response> createTimeOpened(@RequestBody Map<String, List> payload,
                                                     Principal principal)
    {
        Boolean timeOpenedCreated = ownerTimeOpenedDayService.create(payload);

        if(timeOpenedCreated){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("time_opened", "Successfully created"))
                            .message("Successfully created")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Response.builder()
                    .timeStamp(now())
                    .data(Map.of("time_opened", "Bad request"))
                    .message("Bad request")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        }
    }
}
