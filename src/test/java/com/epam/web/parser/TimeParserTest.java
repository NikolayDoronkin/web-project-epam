package com.epam.web.parser;

import org.junit.Assert;
import org.junit.Test;

public class TimeParserTest {

    @Test
    public void testParseWhenInputDataIsCorrect(){
        String inputTime = "16:55";
        TimeParser timeParser = new TimeParser();

        String expected = "16";
        String actual = timeParser.parse(inputTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseWhenInputDataIsIncorrect(){
        String inputTime = "16:55eftgrtgrt";
        TimeParser timeParser = new TimeParser();

        String expected = "16";
        String actual = timeParser.parse(inputTime);

        Assert.assertEquals(expected, actual);
    }

}
