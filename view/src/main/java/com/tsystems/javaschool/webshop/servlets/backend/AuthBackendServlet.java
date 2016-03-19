package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Auth backend servlet.
 */
public class AuthBackendServlet extends HttpServlet {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AuthBackendServlet.class);
    /**
     * The Account service.
     */
    private final AccountService accountService;

    /**
     * The Validation service.
     */
    private final ValidationService validationService;

    /**
     * Instantiates a new Auth backend servlet.
     */
    public AuthBackendServlet() {
        this.accountService = new AccountServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    /**
     * Instantiates a new Auth backend servlet.
     *
     * @param accountSrv    the account service
     * @param validationSrv the validation service
     */
    public AuthBackendServlet(final AccountService accountSrv,
                              final ValidationService validationSrv) {
        this.accountService = accountSrv;
        this.validationService = validationSrv;
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/backend/auth.jsp").forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String isRemember = req.getParameter("remember");

        try {
            User user = accountService.signInUser(email, password);
            //if non-admin user trying to enter backend
            if (user.getRole().equals(UserRole.ROLE_USER)) {
               throw new AccountServiceException(
                       "You don't have permission to enter admin panel");
            }
            if (isRemember != null && isRemember.equals("on")) {
                Cookie cookie = ServletUtils.createCookie("userID",
                        String.valueOf(user.getId()));
                resp.addCookie(cookie);
            }
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(resp.encodeRedirectURL("/backend"));

        } catch (AccountServiceException e) {
            LOGGER.warn("Login failed: "
                    + e.getMessage(), e);
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/backend/auth.jsp")
                    .forward(req, resp);
        }
    }
}
