package com.example.foodapp.product.enumeration;

public enum Availability {
    AVAILABLE("AVAILABLE"),
    NON_AVAILABLE("NON_AVAILABLE");

    private final String availability;

    Availability(String availability){
        this.availability = availability;
    }

    public String getAvailability(){
        return this.availability;
    }
}
