package com.example.foodapp.business.model;

import com.example.foodapp.auth.user.UserProfiles.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;

    @ManyToOne
//    @JoinColumn(name = "user_rating_id")
    private Customer customer;

    @ManyToOne
//    @JoinColumn(name = "business_rating_id")
    private Business business;
}
