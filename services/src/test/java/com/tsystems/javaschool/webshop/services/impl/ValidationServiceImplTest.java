package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Shide on 04.03.2016.
 */
public class ValidationServiceImplTest {

    ValidationService validationService;
    @Before
    public void setUp() throws Exception {
        validationService = new ValidationServiceImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetValidPassword() throws Exception {
        String password = "password";
        String validPass = validationService.getValidPassword(password, password);
        Assert.assertEquals(password, validPass);
    }

    @Test
    public void testGetValidDate() throws Exception {
        String pattern = "dd-MM-yyyy";
        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat(pattern);
        String dateStr = df.format(today);
        Date validDate = validationService.getValidDate(dateStr, pattern);

        Assert.assertEquals(today, validDate);
    }

    @Test
    public void testGetValidEmail() throws Exception {
        String email = "abc@mail.ru";
        String validEmail = validationService.getValidEmail(email);
        Assert.assertEquals(email, validEmail);
    }

    @Test
    public void testGetValidInt() throws Exception {
        int i = 123;
        int validInt = validationService.getValidInt(String.valueOf(i), "q");
        Assert.assertEquals(i, validInt);
    }

    @Test(expected = ServiceException.class)
    public void testGetExceptionPassword() throws Exception {
        String password = "password";
        String password2 = "passwora";
        String validPass = validationService.getValidPassword(password, password2);
    }

    @Test(expected = ServiceException.class)
    public void testGetExceptionDate() throws Exception {
        String pattern = "dd-MM-yyyy";
        String dateStr = "1993-11-02";
        validationService.getValidDate(dateStr, pattern);

    }

    @Test(expected = ServiceException.class)
    public void testGetExceptionEmail() throws Exception {
        String email = "abcmail.ru";
        validationService.getValidEmail(email);
    }

    @Test(expected = ServiceException.class)
    public void testGetExceptionInt() throws Exception {
        validationService.getValidInt("q123", "q");
    }
}