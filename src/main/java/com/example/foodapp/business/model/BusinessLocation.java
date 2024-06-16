package com.example.foodapp.business.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String streetName;
    private String buildingNumber;
    private String flatNumber;
    private int zipCode;
    private String cityName;

    @OneToOne
//    @JoinColumn(name = "business_id")
    @JsonBackReference
    @JsonIgnore
    private Business business;

    public void setBusiness(Business business){
        this.business = business;
        business.setBusinessLocation(this);
    }

    @JsonIgnore
    public Map<String, Object> getLocationDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("streetName", this.getStreetName());
        map.put("buildingNumber", this.getBuildingNumber());
        map.put("flatNumber", this.getFlatNumber());
        map.put("zipCode", this.getZipCode());
        map.put("cityName", this.getCityName());
        return map;
    }
}
