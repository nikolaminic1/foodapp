package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameOfImage;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;
}
