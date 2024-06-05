package com.example.foodapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedRequest {
    private String firstname;
    private String lastname;
    private String phone;
    private Gender gender;

}
