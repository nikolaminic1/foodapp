package com.example.foodapp.business.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private int buildingNumber;
    private int flatNumber;
    private int zipCode;
    private String cityNumber;

    @OneToOne
//    @JoinColumn(name = "business_id")
    @JsonBackReference
    @JsonIgnore
    private Business business;
}
