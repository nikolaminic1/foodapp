package com.example.foodapp.product.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String nameOfImage;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String imageUrl;
    private long size;
}
