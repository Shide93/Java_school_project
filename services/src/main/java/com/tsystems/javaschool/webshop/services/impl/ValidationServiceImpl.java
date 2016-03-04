package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Service for validating request variables.
 */
public class ValidationServiceImpl implements ValidationService {

    @Override
    public String getValidPassword(final String password, final String pasword2) throws ServiceException {
        return password;
    }

    @Override
    public Date getValidDate(final String date, final String pattern)
            throws ServiceException {
        return null;
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date birthDate = null;
//        try {
//            birthDate = format.parse(req.getParameter("birth_date"));
//        } catch (ParseException e) {
//            //wrong date
//            LOGGER.info("user entered wrong date");
//            req.setAttribute("wrongBirth", "Wrong birth date format");
//            req.getRequestDispatcher("profile.jsp").forward(req, resp);
//        }
    }

    @Override
    public String getValidEmail(final String email) throws ServiceException {

        return email;
    }

    @Override
    public int getValidInt(final String intStr, final String fieldName)
            throws ServiceException {
        return 0;
    }
}
