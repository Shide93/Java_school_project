package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Controller for sign in/up pages and user profile.
 */
@Controller
public class SignUpController {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SignUpController.class);
    /**
     * The Validator.
     */
    @Autowired
    @Qualifier("passwordValidator")
    private Validator validator;

    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Prepare user user.
     *
     * @param user the user
     * @return the user
     */
    @ModelAttribute(value = "user")
    public User prepareUser(final User user) {
        user.setRole(UserRole.ROLE_USER);
        return user;
    }

    /**
     * Gets sign up page.
     *
     * @param model the model
     * @return the sign up page
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public final String getSignUpPage(final Model model) {
        return "signup";
    }

    /**
     * Sign up user string.
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param model         the model
     * @param request       the request
     * @return the string
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public final String signUpUser(@ModelAttribute @Valid final User user,
                                   final BindingResult bindingResult,
                                   final Model model,
                                   final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            String password = user.getPassword();
            accountService.signUpUser(user);
            accountService.doAutoLogin(user.getEmail(), password,
                    new WebAuthenticationDetails(request));
        } catch (AccountServiceException e) {
            bindingResult.rejectValue("email", "email.exists");
            LOGGER.warn(e);
            return "signup";
        } catch (AuthenticationException e) {
            LOGGER.warn(e);
            return "redirect:/signin?signup=success";
        }
        return "redirect:/";
    }

}
