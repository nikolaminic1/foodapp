package com.example.foodapp.auth.user.Addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String streetName;
    private int buildingNumber;
    private int flatNumber;
    private int zipCode;
    private String cityNumber;
    private String addressType;
    private boolean isDefault;
}
