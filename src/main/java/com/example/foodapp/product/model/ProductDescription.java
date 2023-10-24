package com.example.foodapp.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key_desc;
    private String value_desc;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
