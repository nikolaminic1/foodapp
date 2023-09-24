package com.example.foodapp.order.enumeration;

public enum RefundStatus {
    IN_PROGRESS("IN_PROGRESS"),
    GRANTED("GRANTED"),
    DENIED("DENIED"),
    RESOLVED("RESOLVED");

    private final String refundStatus;

    RefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundStatus(){
        return refundStatus;
    }
}
