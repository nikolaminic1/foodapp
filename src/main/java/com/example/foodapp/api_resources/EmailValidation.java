package com.example.foodapp.api_resources;


import io.jsonwebtoken.lang.Assert;

import java.util.regex.Pattern;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

public class EmailValidation {
    private static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

//    public static void testUsingSimpleRegex() {
//        String emailAddress = "username@domain.com";
//        String regexPattern = "^(.+)@(\\S+)$";
//        Assert.assertTrue(patternMatches(emailAddress, regexPattern));
//    }

}
