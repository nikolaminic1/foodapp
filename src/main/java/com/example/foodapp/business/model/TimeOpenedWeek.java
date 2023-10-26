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
}
