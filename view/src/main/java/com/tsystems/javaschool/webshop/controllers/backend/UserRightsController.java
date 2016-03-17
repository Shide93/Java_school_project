package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.services.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User rights managing.
 */
@Controller
public class UserRightsController {

    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/backend/users", method = RequestMethod.GET)
    public final String getUserList(final Model model) {
        model.addAttribute("users", accountService.getAll());
        return "backend/users";
    }

    @RequestMapping(value = "/backend/users", method = RequestMethod.POST)
    public final void setRights(@RequestParam final int id,
                                @RequestParam final Boolean isAdmin) {
       accountService.setUserRights(id, isAdmin);
    }

}
