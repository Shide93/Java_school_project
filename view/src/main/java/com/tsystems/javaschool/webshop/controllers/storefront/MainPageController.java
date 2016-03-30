package com.tsystems.javaschool.webshop.controllers.storefront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shide on 30.03.2016.
 */
@Controller
public class MainPageController {
    @RequestMapping(value = "/")
    public final String getMainPage() {

        return "main";
    }
}
