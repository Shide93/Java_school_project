package com.tsystems.javaschool.webshop.controllers.storefront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Shide on 19.03.2016.
 */
@Controller
public class SignInController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public final String getSignInPage() {
        return "signin";
    }

    @RequestMapping(value = "/backend/signin", method = RequestMethod.GET)
    public final String getBackendSignInPage() {

        return "backend/auth";
    }
}
