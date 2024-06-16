package com.example.foodapp.business.model.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDateFormat {
    private Integer openHour;
    private Integer openMinute;
    private Integer closeHour;
    private Integer closeMinute;
}
