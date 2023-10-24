package com.example.foodapp.auth.service;

import com.example.foodapp.auth.dto.EmailDetails;

public interface EmailService {
    String sendEmail(EmailDetails details) throws Exception;
}
