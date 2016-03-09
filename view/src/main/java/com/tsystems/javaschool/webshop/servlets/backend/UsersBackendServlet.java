package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type Users backend servlet.
 */
public class UsersBackendServlet extends HttpServlet {

    /**
     * The Account service.
     */
    private final AccountService accountService;
    /**
     * The Validation service.
     */
    private final ValidationService validationService;

    /**
     * Instantiates a new Users backend servlet.
     */
    public UsersBackendServlet() {
        accountService = new AccountServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    /**
     * Instantiates a new Users backend servlet.
     *
     * @param accountSrv    the account service
     * @param validationSrv the validation service
     */
    public UsersBackendServlet(final AccountService accountSrv,
                               final ValidationService validationSrv) {
        this.accountService = accountSrv;
        this.validationService = validationSrv;
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {
        List<UserEntity> users = accountService.getAll();

        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");
        String isAdmin = req.getParameter("isAdmin");
        if (action.equals("setRights")) {
            Integer id = Integer.parseInt(idStr);
            accountService.setUserRights(id, Boolean.valueOf(isAdmin));

        }
    }
}
