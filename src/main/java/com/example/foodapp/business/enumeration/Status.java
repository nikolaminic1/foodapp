package com.example.foodapp.business.enumeration;

import java.util.Objects;

public enum Status {
    BUSINESS_OPEN("BUSINESS_OPEN"),
    BUSINESS_CLOSED("BUSINESS_CLOSED");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public Boolean getStatus(){
        return !Objects.equals(this.status, BUSINESS_CLOSED.toString());
    }
}
