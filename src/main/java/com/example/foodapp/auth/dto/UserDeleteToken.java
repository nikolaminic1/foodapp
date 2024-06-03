package com.example.foodapp.auth.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDeleteToken {
    @Id
    private UUID uid;
    private String token;
    private String email;
    private Boolean active;
    private LocalDateTime dateCreated;
    private LocalDateTime dateAccessed;
}

