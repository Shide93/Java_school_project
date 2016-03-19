package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for sign in/up pages and user profile.
 */
@Controller
public class SignUpController {

    @Autowired
    @Qualifier("passwordValidator")
    private Validator validator;

    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;

    @InitBinder
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute(value = "user")
    public User prepareUser(final User user) {
        user.setRole(UserRole.ROLE_USER);
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public final String getSignUpPage(final Model model) {
//        User user = new User();
//        user.setRole(UserRole.ROLE_USER);
//        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public final String signUpUser(@ModelAttribute @Valid final User user,
                                   final BindingResult bindingResult,
                                   final Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            accountService.signUpUser(user);

        } catch (AccountServiceException e) {
            bindingResult.rejectValue("email", "email.exists");
            //TODO: log and handle exception
            return "signup";
        }

        return "redirect:/signin?signup=success";
    }
}
