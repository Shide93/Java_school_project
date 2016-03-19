package com.tsystems.javaschool.webshop.controllers.storefront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for sign in/up pages and user profile.
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public final String getSigninPage() {
        return "signin";
    }
}
