package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.Date;

/**
 * Service for validating request variables.
 */
public interface ValidationService {

    /**
     * Returns password if entered passwords are equals.
     *
     * @param password  first password
     * @param password2 the password 2
     * @return valid password
     * @throws ServiceException if passwords not equal
     */
    String getValidPassword(String password, String password2) throws ServiceException;

    /**
     * Validate email and return if it is valid or throw exception.
     *
     * @param email the email
     * @return valid email
     * @throws ServiceException if email not valid
     */
    String getValidEmail(String email) throws ServiceException;

    /**
     * Gets valid int or throw exception.
     *
     * @param intStr    the int str
     * @param fieldName the field name to ad to exception msg
     * @return valid int
     * @throws ServiceException if int not valid
     */
    int getValidInt(String intStr, String fieldName) throws ServiceException;

    /**
     * Gets valid date by given pattern.
     *
     * @param date    the date
     * @param pattern the pattern to validate
     * @return the valid date
     * @throws ServiceException if date not valid
     */
    Date getValidDate(String date, String pattern) throws ServiceException;

}
