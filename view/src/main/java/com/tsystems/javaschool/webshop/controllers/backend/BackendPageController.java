package com.tsystems.javaschool.webshop.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shide on 30.03.2016.
 */
@Controller
public class BackendPageController {
    @RequestMapping(value = "/backend")
    public final String getBackendMainPage() {

        return "backend/backend";
    }
}
