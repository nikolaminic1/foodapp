package com.example.foodapp.auth.controller;


import com.example.foodapp._api.Response;
import com.example.foodapp.auth.dto.AuthenticationResponse;
import com.example.foodapp.auth.dto.LoginRequest;
import com.example.foodapp.auth.dto.RegisterRequest;
import com.example.foodapp.auth.dto.VerifyRequest;
import com.example.foodapp.auth.service.AuthenticationService;
import com.example.foodapp.auth.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/jwt/create")
    public ResponseEntity<AuthenticationResponse> jwtCreate (
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                service.login(request)
        );
    }

    @PostMapping("/jwt/refresh")
    public ResponseEntity<?> jwtRefresh (
            @RequestBody TokenRequest refresh
    ) {
        try {
            return ResponseEntity.ok(
                    service.refreshToken(refresh.getToken())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }

    }
    @PostMapping("/jwt/verify")
    public ResponseEntity<String> jwtVerify (
            @RequestBody String token
    ) {
        try {
            return ResponseEntity.ok(
                    service.verify(token)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(e.getMessage());
        }

    }
}
