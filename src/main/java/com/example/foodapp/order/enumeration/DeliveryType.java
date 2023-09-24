package com.example.foodapp.order.enumeration;

public enum DeliveryType {
    HOME("HOME");

    private final String deliveryType;

    DeliveryType(String deliveryType){
        this.deliveryType = deliveryType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }
}
