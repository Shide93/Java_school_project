package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * Service for validating request variables.
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    /**
     * Email validation RegExp.
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public final String getValidPassword(final String password,
                                         final String password2)
            throws ServiceException {
        if (password.equals(password2)) {
            return password;
        } else {
            throw new ServiceException("passwords are not equals");
        }
    }

    @Override
    public final Date getValidDate(final String date, final String pattern)
            throws ServiceException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
           throw new ServiceException("Birth date is not valid");
        }
    }

    @Override
    public final String getValidEmail(final String email)
            throws ServiceException {
        if (Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            return email;
        } else {
            throw new ServiceException("Email is not valid");
        }
    }

    @Override
    public final int getValidInt(final String intStr, final String fieldName)
            throws ServiceException {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new ServiceException(fieldName + " is not valid", e);
        }
    }
}
