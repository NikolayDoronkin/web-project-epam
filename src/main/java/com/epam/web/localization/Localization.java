package com.epam.web.localization;

public class Localization {

    private final static String russian = "ru";
    private final static String english = "en";
    private final static String spanish = "es";

    public static boolean equals(String value){
        return value.equals(russian) || value.equals(english) || value.equals(spanish);
    }

}
