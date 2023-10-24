package com.example.foodapp.auth.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class ActivationToken {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID uid;
    private String token;
    private String email;
    private Boolean active;
    private LocalDateTime dateCreated;
    private LocalDateTime dateAccessed;

//    public  ActivationToken(UUID uid, String token, String email) {
//        this.uid = uid;
//        this.token = token;
//        this.email = email;
//        this.active = true;
//        this.dateCreated = LocalDateTime.now();
//    }
}
