package com.example.foodapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordConfirmRequest {
    private UUID uid;
    private String token;
    private String new_password;
    private String re_new_password;
}
