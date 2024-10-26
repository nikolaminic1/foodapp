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

    public static Integer convertToInt(long number) {
        if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
            return (int) number;
        } else {
            System.out.println("Error: long value is out of int range.");
            return null;
        }
    }
}
