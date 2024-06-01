package com.example.foodapp.auth.controller;

import com.example.foodapp._api.Response;
import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.service.LogoutService;
import com.example.foodapp.auth.service.UserService;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register (
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<Response> activate (
            @RequestBody ConfirmationRequestResponse request
    ) {
        try {
            String message = service.activate(request);
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.OK)
                            .timeStamp(LocalDateTime.now())
                            .message(message)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout (
            Principal principal
    ) {
        try {
//            String message = logoutService.logout(principal);
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMyProfile (
            Principal principal
    ) {
        try {
            var user = service.getProfile(principal);
            return ResponseEntity.ok()
                    .body(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                    Response.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping("/delete-me")
    public ResponseEntity<Response> deleteMyProfile (
            @RequestBody LoginRequest request,
            Principal principal
    ) {
        try {
            ConfirmationRequestResponse token = service.deleteMyProfile(principal, request);
            return ResponseEntity.ok().body(
                    Response.builder()
                            .data(Map.of("Token", token))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .data(Map.of("Message", e.getMessage()))
                            .build()
            );
        }
    }

    @PostMapping("/delete-me-confirmation")
    public ResponseEntity<Response> deleteMyProfileConfirmation (
            @RequestBody LoginRequest request,
            Principal principal
    ) {
        try {
            ConfirmationRequestResponse token = service.deleteMyProfile(principal, request);
            return ResponseEntity.ok().body(
                    Response.builder()
                            .data(Map.of("Token", token))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .data(Map.of("Message", e.getMessage()))
                            .build()
            );
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> activate (
            @RequestBody ResetPasswordRequest request
    ) {
        try {
            String message = service.resetPassword(request);
            return ResponseEntity.ok(
                    Response.builder()
                            .data(Map.of("Data", message))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .data(Map.of("Message", e.getMessage()))
                            .build()
            );
        }

    }

    @PostMapping("/reset-password-confirmation")
    public ResponseEntity<Response> activate (
            @RequestBody ResetPasswordConfirmRequest request
    ) {
        try {
            String message = service.resetPasswordConfirm(request);
            return ResponseEntity.ok(
                    Response.builder()
                            .data(Map.of("Data", message))
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .data(Map.of("Message", e.getMessage()))
                            .build()
            );
        }

    }
}
