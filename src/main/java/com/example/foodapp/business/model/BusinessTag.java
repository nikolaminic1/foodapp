package com.example.foodapp.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTag {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TagType tagType;
    private String color;
}
