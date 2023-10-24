package com.example.foodapp.product.model;

import com.example.foodapp.auth.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;
    private String ratingDescription;

    @ManyToOne
    @JoinColumn(name = "user_rating_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_rating_id")
    private Product product;
}
