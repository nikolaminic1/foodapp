package com.example.foodapp.business.enumeration;

public enum BusinessRequestStatus {
    GRANTED("GRANTED"),
    IN_PROGRESS("IN_PROGRESS"),
    DENIED("DENIED");

    private final String businessRequestStatus;

    BusinessRequestStatus(String businessRequestStatus){
        this.businessRequestStatus = businessRequestStatus;
    }

    public String getBusinessRequestStatus() {
        return this.businessRequestStatus;
    }

}
