package com.example.foodapp.business.model.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateRequest {
    private String streetName;
    private String buildingNumber;
    private String flatNumber;
    private int zipCode;
    private String cityName;
}
