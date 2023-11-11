package com.example.foodapp.business.model.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeOpenedDayRequest {
    private String timeOpen;
    private String timeClose;
    private Boolean isNonStop;
}
