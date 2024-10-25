package com.example.foodapp.auth.controller;


import com.example.foodapp._api.Response;
import com.example.foodapp.auth.dto.AuthenticationResponse;
import com.example.foodapp.auth.dto.LoginRequest;
import com.example.foodapp.auth.dto.RegisterRequest;
import com.example.foodapp.auth.dto.VerifyRequest;
import com.example.foodapp.auth.service.AuthenticationService;
import com.example.foodapp.auth.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/jwt/create")
    public ResponseEntity<AuthenticationResponse> jwtCreate (
            @RequestBody LoginRequest request
    ) {
        try {
            return ResponseEntity.ok(service.login(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/jwt/refresh")
    public ResponseEntity<?> jwtRefresh (
            @RequestBody TokenRequest refresh
    ) {
        try {
            System.out.println(refresh);
            return ResponseEntity.ok(
                    service.refreshToken(refresh.getToken())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
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

    @PostMapping("/token/verify")
    public ResponseEntity<String> verifyHashedToken(
            @RequestBody Map<String, String> obj
    ) {
        try {
            return ResponseEntity.ok(
                    service.verifyHashedToken(obj.get("token"), obj.get("iv"))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(e.getMessage());
        }
    }
}
