package com.epam.web.parser;

public class TimeParser {

    public TimeParser() {
    }

    public String parse(String inputTime) {
        String[] timeElements = inputTime.split("\\:");
        return timeElements[0];
    }
}
