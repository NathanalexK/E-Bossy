package com.project.ebossy.util;

public class Utility {
    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
