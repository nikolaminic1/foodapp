package com.example.foodapp.order.exception;


import com.stripe.exception.StripeException;

public class APIConnectionException extends StripeException {
    private static final long serialVersionUID = 2L;

    public APIConnectionException(String message, String requestId, String code, Integer statusCode) {
        super(message, requestId, code, statusCode);
    }
}
