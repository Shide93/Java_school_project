package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Validation service impl test.
 */
@SuppressWarnings("CheckStyle")
public class ValidationServiceImplTest {

    /**
     * The Validation service.
     */
    private ValidationService validationService;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        validationService = new ValidationServiceImpl();
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test get valid password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetValidPassword() throws Exception {
        String password = "password";
        String validPass = validationService.getValidPassword(password, password);
        Assert.assertEquals(password, validPass);
    }

    /**
     * Test get valid date.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetValidDate() throws Exception {
        String pattern = "dd-MM-yyyy";
        String dateStr = "12-11-1290";
        Date validDate = validationService.getValidDate(dateStr, pattern);
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        String reportDate = format.format(validDate);
        Assert.assertEquals(dateStr, reportDate);
    }

    /**
     * Test get valid email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetValidEmail() throws Exception {
        String email = "abc@mail.ru";
        String validEmail = validationService.getValidEmail(email);
        Assert.assertEquals(email, validEmail);
    }

    /**
     * Test get valid int.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetValidInt() throws Exception {
        int i = 123;
        int validInt = validationService.getValidInt(String.valueOf(i), "q");
        Assert.assertEquals(i, validInt);
    }

    /**
     * Test get exception password.
     *
     * @throws Exception the exception
     */
    @Test(expected = ServiceException.class)
    public void testGetExceptionPassword() throws Exception {
        String password = "password";
        String password2 = "passwora";
        String validPass = validationService.getValidPassword(password, password2);
    }

    /**
     * Test get exception date.
     *
     * @throws Exception the exception
     */
    @Test(expected = ServiceException.class)
    public void testGetExceptionDate() throws Exception {
        String pattern = "dd-MM-yyyy";
        String dateStr = "1993/11/02";
        validationService.getValidDate(dateStr, pattern);

    }

    /**
     * Test get exception email.
     *
     * @throws Exception the exception
     */
    @Test(expected = ServiceException.class)
    public void testGetExceptionEmail() throws Exception {
        String email = "abcmail.ru";
        validationService.getValidEmail(email);
    }

    /**
     * Test get exception int.
     *
     * @throws Exception the exception
     */
    @Test(expected = ServiceException.class)
    public void testGetExceptionInt() throws Exception {
        validationService.getValidInt("q123", "q");
    }
}
