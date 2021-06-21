package com.epam.web.validate;

public class ReviewValidator {

    public static boolean validateNumber(String value){
        if(value == null){
            return false;
        }
        return value.matches("[\\d]+");
    }

    public static boolean validateDigit(String value){
        if(value == null){
            return false;
        }
        return value.matches("[0-5]");
    }

    public static boolean validateText(String value){
        if(value == null){
            return false;
        }
        return value.matches("[a-zA-Zа-яА-Я0-9\\s]{1,150}");
    }

}
