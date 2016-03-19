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
    public static final int MIN_PASS_LENGTH = 8;
    public static final int MAX_PASS_LENGTH = 20;

    @Override
    public final boolean supports(final Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public final void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "confirmPassword", "passwordConfirm.required");
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;
        if (user.getPassword().length() < MIN_PASS_LENGTH
                || user.getPassword().length() > MAX_PASS_LENGTH) {
            errors.rejectValue("password", "password.wrongLength");
            return;
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "password.notmatch");
        }
    }
}
