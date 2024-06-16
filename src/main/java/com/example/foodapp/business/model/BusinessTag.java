package com.example.foodapp.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> getTagDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("type", this.getTagType().toString());
        map.put("color", this.getColor());
        return map;
    }
}
