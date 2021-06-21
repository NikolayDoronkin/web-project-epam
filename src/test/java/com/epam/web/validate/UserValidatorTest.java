package com.epam.web.validate;

import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {

    @Test
    public void testIsEqualShouldReturnsTrue(){
        String password = "1234";
        String repeatPassword = "1234";

        boolean actual = UserValidator.isEqual(password, repeatPassword);

        Assert.assertTrue(actual);

    }

    @Test
    public void testIsEqualShouldReturnsFalse(){
        String password = "1234";
        String repeatPassword = "12345";

        boolean actual = UserValidator.isEqual(password, repeatPassword);

        Assert.assertFalse(actual);

    }

    @Test
    public void testIsValidWhenAllDataIsValid(){
        String userName = "narko228";
        String password = "epamlab229";

        boolean actual = UserValidator.isValid(userName, password);

        Assert.assertTrue(actual);

    }

    @Test
    public void testIsValidWhenAllDataIsInvalid(){
        String userName = "na";
        String password = "epamlab229";

        boolean actual = UserValidator.isValid(userName, password);

        Assert.assertFalse(actual);

    }



}
