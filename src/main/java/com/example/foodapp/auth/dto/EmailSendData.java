package com.example.foodapp.auth.dto;

import lombok.Data;

@Data
public class EmailSendData {
    private String email;
    private String subject;
    private String title;
}
