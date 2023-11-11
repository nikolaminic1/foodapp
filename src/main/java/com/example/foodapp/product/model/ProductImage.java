package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "product" })
@EqualsAndHashCode(exclude = {"product"})
@ToString(exclude = {"product"})
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;

    @JsonView(View.Public.class)
    private String nameOfImage;
    @JsonView(View.Public.class)
    private String description;
    @CreationTimestamp
    @JsonView(View.Public.class)
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    @JsonView(View.Public.class)
    private LocalDateTime dateUpdated;
    @JsonView(View.Public.class)
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "product_id")
//    @JsonIgnore
    @JsonManagedReference
    private Product product;
}
