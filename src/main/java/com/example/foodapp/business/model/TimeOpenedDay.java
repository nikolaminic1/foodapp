package com.example.foodapp.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeOpenedDay {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String timeOpen;
    private String timeClose;
    private Boolean isNonStop;

}
