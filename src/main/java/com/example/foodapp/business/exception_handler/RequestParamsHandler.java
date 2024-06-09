package com.example.foodapp.business.exception_handler;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@ControllerAdvice
public class RequestParamsHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException exception) {
        String name = exception.getParameterName();
        System.out.println(name);
    }
}
