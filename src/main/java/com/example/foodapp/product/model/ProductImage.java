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
import java.util.HashMap;
import java.util.Map;

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
    private Long id;
    private String nameOfImage;
    private String description;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "product_id")
//    @JsonIgnore
    @JsonManagedReference
    private Product product;

    public Object getAdminProductImageDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("nameOfImage", this.getNameOfImage());
        map.put("description", this.getDescription());
        map.put("dateCreated", this.getDateCreated());
        map.put("dateUpdated", this.getDateUpdated());
        map.put("imageUrl", this.getImageUrl());
        return map;
    }
}
