package com.example.foodapp.auth.controller;

import com.example.foodapp._api.Response;
import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.service.LogoutService;
import com.example.foodapp.auth.service.UserService;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    @PostMapping("/resend-activation-email")
    @ResponseBody
    public ResponseEntity<Object> resendActivationEmail (
            @RequestBody String email
    ) {
        try {
            return ResponseEntity.ok(service.resendActivationEmail(email));
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

    @PostMapping("/me")
    public ResponseEntity<Object> updateProfile (
            @RequestBody UserUpdatedRequest request,
            Principal principal
    ) {
        try {
            var user = service.updateProfile(principal, request);
            return ResponseEntity.ok().body(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-me")
    public ResponseEntity<?> deleteMyProfile (
            Principal principal
    ) {
        try {
            String token = service.deleteMyProfile(principal);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete-me-confirmation")
    public ResponseEntity<?> deleteMyProfileConfirmation (
            @RequestBody UserDeleteRequest request
    ) {
        try {
            // TODO: Password should be decoded in some other way in order to allow password check
            String token = service.deleteProfileConfirmation(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> activate (
            @RequestBody ResetPasswordRequest request
    ) {
        try {
            String message = service.resetPassword(request);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/reset-password-confirmation")
    public ResponseEntity<?> activate (
            @RequestBody ResetPasswordConfirmRequest request
    ) {
        try {
            return ResponseEntity.ok(service.resetPasswordConfirm(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
