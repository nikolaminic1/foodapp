package com.example.foodapp.auth.service.implementation;

import com.example.foodapp.auth.dto.EmailDetails;
import com.example.foodapp.auth.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmailServiceImplementation implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender mailSender;

    @Override
    public String sendEmail(EmailDetails details) throws Exception {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMessageBody());
            mailMessage.setSubject(details.getSubject());
            mailSender.send(mailMessage);
            return "OK";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
