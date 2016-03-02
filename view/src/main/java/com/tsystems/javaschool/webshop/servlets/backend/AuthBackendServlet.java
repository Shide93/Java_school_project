package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
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
    private AccountService accountService;

    /**
     * Instantiates a new Auth backend servlet.
     */
    public AuthBackendServlet() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/backend/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String isRemember = req.getParameter("remember");

        try {
            UserEntity user = accountService.signInUser(email, password);

            if (!user.getIsAdmin()) {        //if non-admin user trying to enter backend
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
