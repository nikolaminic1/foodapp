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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDayMonday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDayTuesday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDayWednesday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDayThursday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDayFriday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDaySaturday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TimeOpenedDay timeOpenedDaySunday;

    @JsonIgnore
    public Map<String, Object> getWorkingTimeMonday() {
        String open = this.timeOpenedDayMonday.getTimeOpen();
        String close = this.timeOpenedDayMonday.getTimeClose();
        Boolean nonstop = this.timeOpenedDayMonday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeTuesday() {
        String open = this.timeOpenedDayTuesday.getTimeOpen();
        String close = this.timeOpenedDayTuesday.getTimeClose();
        Boolean nonstop = this.timeOpenedDayTuesday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeWednesday() {
        String open = this.timeOpenedDayWednesday.getTimeOpen();
        String close = this.timeOpenedDayWednesday.getTimeClose();
        Boolean nonstop = this.timeOpenedDayWednesday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeThursday() {
        String open = this.timeOpenedDayThursday.getTimeOpen();
        String close = this.timeOpenedDayThursday.getTimeClose();
        Boolean nonstop = this.timeOpenedDayThursday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeFriday() {
        String open = this.timeOpenedDayFriday.getTimeOpen();
        String close = this.timeOpenedDayFriday.getTimeClose();
        Boolean nonstop = this.timeOpenedDayFriday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeSaturday() {
        String open = this.timeOpenedDaySaturday.getTimeOpen();
        String close = this.timeOpenedDaySaturday.getTimeClose();
        Boolean nonstop = this.timeOpenedDaySaturday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }

    @JsonIgnore
    public Map<String, Object> getWorkingTimeSunday() {
        String open = this.timeOpenedDaySunday.getTimeOpen();
        String close = this.timeOpenedDaySunday.getTimeClose();
        Boolean nonstop = this.timeOpenedDaySunday.getIsNonStop();
        var map = new HashMap<String, Object>();
        map.put("open", open);
        map.put("close", close);
        map.put("nonstop", nonstop);
        return map;
    }
}
