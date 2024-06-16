package com.example.foodapp.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TimeOpenedWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer mondayTimeOpenHour;
    private Integer mondayTimeOpenMinute;
    private Integer mondayTimeCloseHour;
    private Integer mondayTimeCloseMinute;
    private Boolean mondayIsNonStop;

    private Integer tuesdayTimeOpenHour;
    private Integer tuesdayTimeOpenMinute;
    private Integer tuesdayTimeCloseHour;
    private Integer tuesdayTimeCloseMinute;
    private Boolean tuesdayIsNonStop;

    private Integer wednesdayTimeOpenHour;
    private Integer wednesdayTimeOpenMinute;
    private Integer wednesdayTimeCloseHour;
    private Integer wednesdayTimeCloseMinute;
    private Boolean wednesdayIsNonStop;

    private Integer thursdayTimeOpenHour;
    private Integer thursdayTimeOpenMinute;
    private Integer thursdayTimeCloseHour;
    private Integer thursdayTimeCloseMinute;
    private Boolean thursdayIsNonStop;

    private Integer fridayTimeOpenHour;
    private Integer fridayTimeOpenMinute;
    private Integer fridayTimeCloseHour;
    private Integer fridayTimeCloseMinute;
    private Boolean fridayIsNonStop;

    private Integer saturdayTimeOpenHour;
    private Integer saturdayTimeOpenMinute;
    private Integer saturdayTimeCloseHour;
    private Integer saturdayTimeCloseMinute;
    private Boolean saturdayIsNonStop;

    private Integer sundayTimeOpenHour;
    private Integer sundayTimeOpenMinute;
    private Integer sundayTimeCloseHour;
    private Integer sundayTimeCloseMinute;
    private Boolean sundayIsNonStop;

    public Map<String, Object> getWorkingTime() {
        Map<String, Object> workingTime = new HashMap<>();
        Map<String, Object> monday = new HashMap<>();
        Map<String, Object> tuesday = new HashMap<>();
        Map<String, Object> wednesday = new HashMap<>();
        Map<String, Object> thursday = new HashMap<>();
        Map<String, Object> friday = new HashMap<>();
        Map<String, Object> saturday = new HashMap<>();
        Map<String, Object> sunday = new HashMap<>();

        monday.put("openHour", this.getMondayTimeOpenHour());
        monday.put("openMinute", this.getMondayTimeOpenMinute());
        monday.put("closeHour", this.getMondayTimeCloseHour());
        monday.put("closeMinute", this.getMondayTimeCloseMinute());
        monday.put("nonstop", this.getMondayIsNonStop());

        tuesday.put("openHour", this.getTuesdayTimeOpenHour());
        tuesday.put("openMinute", this.getTuesdayTimeOpenMinute());
        tuesday.put("closeHour", this.getTuesdayTimeCloseHour());
        tuesday.put("closeMinute", this.getTuesdayTimeCloseMinute());
        tuesday.put("nonstop", this.getTuesdayIsNonStop());

        wednesday.put("openHour", this.getWednesdayTimeOpenHour());
        wednesday.put("openMinute", this.getWednesdayTimeOpenMinute());
        wednesday.put("closeHour", this.getWednesdayTimeCloseHour());
        wednesday.put("closeMinute", this.getWednesdayTimeCloseMinute());
        wednesday.put("nonstop", this.getWednesdayIsNonStop());

        thursday.put("openHour", this.getThursdayTimeOpenHour());
        thursday.put("openMinute", this.getThursdayTimeOpenMinute());
        thursday.put("closeHour", this.getThursdayTimeCloseHour());
        thursday.put("closeMinute", this.getThursdayTimeCloseMinute());
        thursday.put("nonstop", this.getThursdayIsNonStop());

        friday.put("openHour", this.getFridayTimeOpenHour());
        friday.put("openMinute", this.getFridayTimeOpenMinute());
        friday.put("closeHour", this.getFridayTimeCloseHour());
        friday.put("closeMinute", this.getFridayTimeCloseMinute());
        friday.put("nonstop", this.getFridayIsNonStop());

        saturday.put("openHour", this.getSaturdayTimeOpenHour());
        saturday.put("openMinute", this.getSaturdayTimeOpenMinute());
        saturday.put("closeHour", this.getSaturdayTimeCloseHour());
        saturday.put("closeMinute", this.getSaturdayTimeCloseMinute());
        saturday.put("nonstop", this.getSaturdayIsNonStop());

        sunday.put("openHour", this.getSundayTimeOpenHour());
        sunday.put("openMinute", this.getSundayTimeOpenMinute());
        sunday.put("closeHour", this.getSundayTimeCloseHour());
        sunday.put("closeMinute", this.getSundayTimeCloseMinute());
        sunday.put("nonstop", this.getSundayIsNonStop());

        workingTime.put("monday", monday);
        workingTime.put("tuesday", tuesday);
        workingTime.put("wednesday", wednesday);
        workingTime.put("thursday", thursday);
        workingTime.put("friday", friday);
        workingTime.put("saturday", saturday);
        workingTime.put("sunday", sunday);

        return workingTime;
    }

    }
