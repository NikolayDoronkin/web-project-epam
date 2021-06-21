package com.epam.web.validate;

public class UserValidator {

    public static boolean isEqual(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

    public static boolean isValid(String userName, String password){
        return userName.matches("[a-zA-Z0-9]{6}") && password.matches("[a-zA-Z0-9]{6}");
    }

    public static boolean isValid(String param){
        return param.matches("[a-zA-Z0-9]{6}");
    }


}
