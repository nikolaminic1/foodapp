package com.example.foodapp.business.model.Requests;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessTag;
import com.example.foodapp.business.model.TimeOpenedWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Double priceOfDelivery;
    private Double priceOfOrderForFreeDelivery;
    private List<Long> tags;
//    private TimeOpenedWeekRequest timeOpened;
//    private TimeOpenedWeekListRequest tm_op;
    private Boolean mondayNonstop;
    private ArrayList<String> mondayOpenClose;

    private Boolean TuesdayNonstop;
    private ArrayList<String> TuesdayOpenClose;

    private Boolean wednesdayNonstop;
    private ArrayList<String> wednesdayOpenClose;

    private Boolean thursdayNonstop;
    private ArrayList<String> thursdayOpenClose;

    private Boolean fridayNonstop;
    private ArrayList<String> fridayOpenClose;

    private Boolean saturdayNonstop;
    private ArrayList<String> saturdayOpenClose;

    private Boolean sundayNonstop;
    private ArrayList<String> sundayOpenClose;


    public static void setTimeOpened(Business business, BusinessUpdateRequest request) {
        TimeOpenedWeek openedWeek;
        if (business.getTimeOpened() != null) {
            openedWeek = business.getTimeOpened();
        } else {
            openedWeek = new TimeOpenedWeek();
        }
        ReturnDateFormat monday = parseDate(request.getMondayOpenClose());
        ReturnDateFormat tuesday = parseDate(request.getTuesdayOpenClose());
        ReturnDateFormat wednesday = parseDate(request.getWednesdayOpenClose());
        ReturnDateFormat thursday = parseDate(request.getThursdayOpenClose());
        ReturnDateFormat friday = parseDate(request.getFridayOpenClose());
        ReturnDateFormat saturday = parseDate(request.getSaturdayOpenClose());
        ReturnDateFormat sunday = parseDate(request.getSundayOpenClose());

        openedWeek.setMondayTimeOpenHour(monday.getOpenHour());
        openedWeek.setMondayTimeOpenMinute(monday.getOpenMinute());
        openedWeek.setMondayTimeCloseHour(monday.getCloseHour());
        openedWeek.setMondayTimeCloseMinute(monday.getCloseMinute());

        openedWeek.setMondayIsNonStop(!(request.getMondayNonstop() == null || !request.getMondayNonstop()));

        openedWeek.setTuesdayTimeOpenHour(tuesday.getOpenHour());
        openedWeek.setTuesdayTimeOpenMinute(tuesday.getOpenMinute());
        openedWeek.setTuesdayTimeCloseHour(tuesday.getCloseHour());
        openedWeek.setTuesdayTimeCloseMinute(tuesday.getCloseMinute());

        openedWeek.setTuesdayIsNonStop(!(request.getThursdayNonstop() == null || !request.getThursdayNonstop()));

        openedWeek.setWednesdayTimeOpenHour(wednesday.getOpenHour());
        openedWeek.setWednesdayTimeOpenMinute(wednesday.getOpenMinute());
        openedWeek.setWednesdayTimeCloseHour(wednesday.getCloseHour());
        openedWeek.setWednesdayTimeCloseMinute(wednesday.getCloseMinute());

        openedWeek.setWednesdayIsNonStop(!(request.getWednesdayNonstop() == null || !request.getWednesdayNonstop()));

        openedWeek.setThursdayTimeOpenHour(thursday.getOpenHour());
        openedWeek.setThursdayTimeOpenMinute(thursday.getOpenMinute());
        openedWeek.setThursdayTimeCloseHour(thursday.getCloseHour());
        openedWeek.setThursdayTimeCloseMinute(thursday.getCloseMinute());

        openedWeek.setThursdayIsNonStop(!(request.getThursdayNonstop() == null || !request.getThursdayNonstop()));

        openedWeek.setFridayTimeOpenHour(friday.getOpenHour());
        openedWeek.setFridayTimeOpenMinute(friday.getOpenMinute());
        openedWeek.setFridayTimeCloseHour(friday.getCloseHour());
        openedWeek.setFridayTimeCloseMinute(friday.getCloseMinute());

        openedWeek.setFridayIsNonStop(!(request.getFridayNonstop() == null || !request.getFridayNonstop()));

        openedWeek.setSaturdayTimeOpenHour(saturday.getOpenHour());
        openedWeek.setSaturdayTimeOpenMinute(saturday.getOpenMinute());
        openedWeek.setSaturdayTimeCloseHour(saturday.getCloseHour());
        openedWeek.setSaturdayTimeCloseMinute(saturday.getCloseMinute());

        openedWeek.setSaturdayIsNonStop(!(request.getSaturdayNonstop() == null || !request.getSaturdayNonstop()));

        openedWeek.setSundayTimeOpenHour(sunday.getOpenHour());
        openedWeek.setSundayTimeOpenMinute(sunday.getOpenMinute());
        openedWeek.setSundayTimeCloseHour(sunday.getCloseHour());
        openedWeek.setSundayTimeCloseMinute(sunday.getCloseMinute());

        openedWeek.setSundayIsNonStop(!(request.getSundayNonstop() == null || !request.getSundayNonstop()));
    }

    public static ReturnDateFormat parseDate(ArrayList<String> timeObjs) {
        ReturnDateFormat map = new ReturnDateFormat();
        String open = timeObjs.get(0);
        String close = timeObjs.get(1);
        System.out.println("----------------");

        System.out.println(open);
        System.out.println(close);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        try {
            LocalDateTime openDate = LocalDateTime.parse(open, formatter);
            LocalDateTime closeDate = LocalDateTime.parse(close, formatter);

            System.out.println(openDate);
            System.out.println(closeDate);

            Integer openHour = openDate.getHour();
            Integer openMinute = openDate.getMinute();
            Integer closeHour = closeDate.getHour();
            Integer closeMinute = closeDate.getMinute();

            System.out.println(openHour);
            System.out.println(openMinute);
            System.out.println(closeHour);
            System.out.println(closeMinute);

            System.out.println("----------------");
            map.setOpenHour(openHour);
            map.setOpenMinute(openMinute);

            if (closeHour < openHour){
                map.setCloseHour(openHour);
                map.setCloseMinute(0);
            } else if (closeHour.equals(openHour)){
                if (closeMinute < openMinute) {
                    map.setCloseMinute(openMinute);
                } else {
                    map.setCloseMinute(closeMinute);
                }
                map.setCloseHour(closeHour);
            } else {
                map.setCloseHour(closeHour);
                map.setCloseMinute(closeMinute);
            }


        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return map;
    }

}

