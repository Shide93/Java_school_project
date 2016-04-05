package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
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

    private static final String BACKEND_USERS_PAGE = "/backend/users";
    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = BACKEND_USERS_PAGE, method = RequestMethod.GET)
    public final String getUserList(final Model model) {
        model.addAttribute("users", accountService.getAll());
        return BACKEND_USERS_PAGE;
    }

    @RequestMapping(value = BACKEND_USERS_PAGE, method = RequestMethod.POST)
    public final void setRights(@RequestParam final int id,
                                @RequestParam final boolean isAdmin) {
        UserRole role;
        if (isAdmin) {
            role = UserRole.ROLE_ADMIN;
        } else {
            role = UserRole.ROLE_USER;
        }
       accountService.setUserRights(id, role);
    }

}
