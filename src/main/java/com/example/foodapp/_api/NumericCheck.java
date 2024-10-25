package com.example.foodapp._api;

public class NumericCheck {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isPositiveLong(String str) {
        try {
            long value = Long.parseLong(str);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
