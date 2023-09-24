package com.example.foodapp.api_resources;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParsedDate {
    public static List parseDate(List day){
        String start = (String) day.get(0);
        String stop = (String) day.get(1);

        ZonedDateTime zdtStart
                = ZonedDateTime
                .parse(start,
                        DateTimeFormatter
                                .ISO_ZONED_DATE_TIME);

        ZonedDateTime zdtStop
                = ZonedDateTime
                .parse(stop,
                        DateTimeFormatter
                                .ISO_ZONED_DATE_TIME);

        String startHours = String.valueOf(zdtStart.getHour() + 1);
        String stopHours = String.valueOf(zdtStop.getHour() + 1);

        System.out.println(startHours);
        System.out.println(stopHours);

        List<String> list = new ArrayList<>();
        list.add(startHours);
        list.add(stopHours);
        return list;
    }
}
