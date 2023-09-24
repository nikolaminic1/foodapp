package com.example.foodapp.business.model;

import com.example.foodapp.auth.user.UserProfiles.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String review;

    @OneToOne
    private Customer customer;

    @ManyToOne
    private Business business;
}
