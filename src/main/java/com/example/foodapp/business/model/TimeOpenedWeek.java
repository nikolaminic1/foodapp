package com.example.foodapp.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeOpenedWeek {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDayMonday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDayTuesday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDayWednesday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDayThursday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDayFriday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDaySaturday;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeOpenedDay timeOpenedDaySunday;
}
