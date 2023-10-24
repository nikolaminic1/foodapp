package com.example.foodapp.auth.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private String subject;
}
