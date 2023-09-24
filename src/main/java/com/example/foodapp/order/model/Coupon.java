package com.example.foodapp.order.model;

import com.example.foodapp.auth.user.UserProfiles.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private double amount;
    private LocalDateTime dateSent;
    private LocalDateTime dateUsed;
    private LocalDateTime dataExpires;
    private boolean isApplied;

    @ManyToOne
    private Customer customer;
}
