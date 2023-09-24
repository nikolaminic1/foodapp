package com.example.foodapp.business.enumeration;

public enum Status {
    BUSINESS_OPEN("BUSINESS_OPEN"),
    BUSINESS_CLOSED("BUSINESS_CLOSED");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
