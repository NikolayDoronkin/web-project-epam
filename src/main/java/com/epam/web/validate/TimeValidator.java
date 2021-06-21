package com.epam.web.validate;

import com.epam.web.parser.TimeParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeValidator {

    private final TimeParser timeParser;
    private final int MIN_VALID_HOUR = 10;
    private final int MAX_VALID_HOUR = 19;


    public TimeValidator() {
        timeParser = new TimeParser();
    }


    public boolean validate(String inputTime) {
        try{
            int inputHours = createHours(timeParser.parse(inputTime));
            return inputHours < MAX_VALID_HOUR && inputHours > MIN_VALID_HOUR;
        }catch (Exception e){
            return false;
        }

    }

    private int createHours(String inputHours){
        return Integer.parseInt(inputHours);
    }

}
