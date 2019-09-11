package com.space.utils;

public class Utils {
    public static Long getLong(String value) {
        Long longValue;
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
