package com.example.foodapp.product.model;


import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameOfImage;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String imageUrl;
    private long size;

    public Map<String, Object> getImageDetail () {
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
