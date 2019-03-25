package com.questionpro;

public class Utils {
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isBoolean(String str) {
        try {
            Boolean.parseBoolean(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
