package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.Address;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shide on 20.03.2016.
 */
@Controller
public class UserProfileController {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserProfileController.class);

    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;
    /**
     * The Order service.
     */
    @Autowired
    private OrderService orderService;


    /**
     * The Sign up controller.
     */
    @Autowired
    private SignUpController signUpController;


    /**
     * The Authentication manager.
     */
    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * The Validator.
     */
    @Autowired
    @Qualifier("passwordValidator")
    private Validator passwordValidator;

    /**
     * Gets user.
     *
     * @param user      the user
     * @param model     the model
     * @param principal the principal
     */
    @ModelAttribute
    public final  void populateModel(final User user,
                                     final Model model,
                               final Principal principal) {

        User oldUser = accountService.getUserByEmail(principal.getName());
        user.setEmail(oldUser.getEmail());
        user.setPassword(oldUser.getPassword());
        user.setRole(oldUser.getRole());
        user.setId(oldUser.getId());
        model.addAttribute("user", user);
        model.addAttribute("credentials", user);
    }

    @InitBinder
    public final void initBinderProfile(final WebDataBinder binder) {

        String format = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true, format.length());
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @InitBinder(value = "credentials")
    public final void initBinderCredentials(final WebDataBinder binder) {
        binder.setValidator(passwordValidator);
    }

    /**
     * Gets profile.
     *
     * @param model     the model
     * @param principal the principal
     * @return the profile
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(final Model model,
                             final Principal principal) {
        User user = accountService.getUserByEmail(principal.getName());
        if (user.getAddress() == null) {
            user.setAddress(new Address());
        }
        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Save profile string.
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param model         the model
     * @return the string
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@ModelAttribute(value = "user")
                                  @Valid final User user,
                              final BindingResult bindingResult,
                              final Model model) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        accountService.saveProfile(user);

        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile/credentials",
            method = RequestMethod.GET)
    public String getCredentialsPage(final Model model,
                             final Principal principal) {
        User user = new User();
        user.setEmail(principal.getName());
        model.addAttribute("credentials", user);
        return "credentials";
    }

    @RequestMapping(value = "/profile/credentials",
            method = RequestMethod.POST)
    public String saveCredentials(@ModelAttribute(value = "credentials")
                                      @Valid final User credentials,
                                  final BindingResult bindingResult,
                                  final Model model,
                                  final Principal principal,
                                  final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "credentials";
        }
        try {
            String password = credentials.getPassword();
            accountService.saveCredentials(credentials, principal.getName());
            accountService.doAutoLogin(credentials.getEmail(), password,
                    new WebAuthenticationDetails(request));
        } catch (AccountServiceException e) {
            bindingResult.rejectValue("email", "email.exists");
            //TODO: log and handle exception
            return "credentials";
        } catch (AuthenticationException e) {
            LOGGER.error("Failure in autoLogin", e);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/profile/credentials";
    }

    @RequestMapping(value = "/profile/orders", method = RequestMethod.GET)
    public String getUserOrdersPage(final Model model,
                                    final Principal principal) {
        User user = accountService.getUserByEmail(principal.getName());
        model.addAttribute("orders", orderService.getAllByUser(user.getId()));
        return "profileOrders";
    }
}
