package com.epam.web.validate;

import org.junit.Assert;
import org.junit.Test;

public class ReviewValidatorTest {

    @Test
    public void validateDigitWhenInputCorrect(){
        String value = "5";

        boolean actual = ReviewValidator.validateDigit(value);

        Assert.assertTrue(actual);
    }

    @Test
    public void validateDigitWhenIncorrectData(){
        String value = "34";

        boolean actual = ReviewValidator.validateDigit(value);

        Assert.assertFalse(actual);
    }

    @Test
    public void validateNumberWhenNull(){
        String value = "5";

        boolean actual = ReviewValidator.validateNumber(value);

        Assert.assertTrue(actual);
    }

    @Test
    public void validateTextWhenEmpty(){
        String value = "";

        boolean actual = ReviewValidator.validateNumber(value);

        Assert.assertFalse(actual);
    }

    @Test
    public void validateTextWhenRussianText(){
        String value = "кеф";

        boolean actual = ReviewValidator.validateText(value);

        Assert.assertTrue(actual);
    }

    @Test
    public void validateTextWhenEnglishText(){
        String value = "kea";

        boolean actual = ReviewValidator.validateText(value);

        Assert.assertTrue(actual);
    }

    @Test
    public void validateTextWhenOutOfBound(){
        String value = "TestingTestingTestingTe" +
                "stingTestingTestingTestingTestingTestingTes" +
                "tingTestingTestingTestingTestingTestingTestingTestingTe" +
                "stingTestingTestingTestingTestingTestingTestingTestingTestingTestingTes" +
                "tingTestingTestingTestingTestingTestingTestingTestingTestingTestingTestingTestingTest" +
                "ingTestingTestingTestingTestingTestingTestingTestingTestingTestingTestingTesting";

        boolean actual = ReviewValidator.validateText(value);

        Assert.assertFalse(actual);
    }




}
