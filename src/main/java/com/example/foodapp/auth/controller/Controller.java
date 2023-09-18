package com.example.foodapp.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/controller")
public class Controller {

    @GetMapping("/ok")
    public ResponseEntity<String> okResponse() {
        return ResponseEntity.ok(
                "Everything ok"
        );
    }
}
