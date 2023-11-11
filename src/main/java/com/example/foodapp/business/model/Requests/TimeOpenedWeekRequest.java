package com.example.foodapp.business.model.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeOpenedWeekRequest {
    private TimeOpenedDayRequest monday;
    private TimeOpenedDayRequest tuesday;
    private TimeOpenedDayRequest wednesday;
    private TimeOpenedDayRequest thursday;
    private TimeOpenedDayRequest friday;
    private TimeOpenedDayRequest saturday;
    private TimeOpenedDayRequest sunday;
}
