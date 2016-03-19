package com.tsystems.javaschool.webshop.dao.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Shide on 16.03.2016.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(final Phone paramA) {
    }

    @Override
    public final boolean isValid(final String phoneNo,
                                 final ConstraintValidatorContext ctx) {
        if (phoneNo == null) {
            return true;
        }
        //validate phone numbers of format "1234567890"
        return phoneNo.matches("\\d{10}")
                //validating phone number with -, . or spaces
                || phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")
                //validating phone number with extension length from 3 to 5
                || phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")
                //validating phone number where area code is in braces ()
                || phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }

}
