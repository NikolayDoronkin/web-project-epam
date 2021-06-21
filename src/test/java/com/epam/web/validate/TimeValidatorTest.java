package com.epam.web.validate;

import org.junit.Assert;
import org.junit.Test;


public class TimeValidatorTest {

    @Test
    public void testValidateWhenInputTimeIsValid(){
        String inputTime = "16:55";
        TimeValidator timeValidator = new TimeValidator();

        boolean expected = timeValidator.validate(inputTime);

        Assert.assertTrue(expected);
    }

    @Test
    public void testValidateWhenInputTimeIsInvalid(){
        String inputTime = "22:55";
        TimeValidator timeValidator = new TimeValidator();

        boolean expected = timeValidator.validate(inputTime);

        Assert.assertFalse(expected);
    }
}
