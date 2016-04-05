package com.tsystems.javaschool.webshop.dao.validation;

import com.tsystems.javaschool.webshop.dao.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validates entered passwords before hashing.
 */
@Component(value = "passwordValidator")
public class PasswordValidator implements Validator {
    /**
     * The constant MIN_PASS_LENGTH.
     */
    public static final int MIN_PASS_LENGTH = 8;
    /**
     * The constant MAX_PASS_LENGTH.
     */
    public static final int MAX_PASS_LENGTH = 20;
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "password";
    /**
     * The constant CONFIRM_PASSWORD.
     */
    public static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public final boolean supports(final Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public final void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                PASSWORD, "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                CONFIRM_PASSWORD, "passwordConfirm.required");
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;
        if (user.getPassword().length() < MIN_PASS_LENGTH
                || user.getPassword().length() > MAX_PASS_LENGTH) {
            errors.rejectValue(PASSWORD, "password.wrongLength");
            return;
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue(PASSWORD, "password.notmatch");
        }
    }
}
