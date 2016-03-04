package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet user to sign in users.
 */
public class SignInServlet extends HttpServlet {


    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SignInServlet.class);

    /**
     * The Account service.
     */
    private AccountService accountService;
    /**
     * The Validation service.
     */
    private ValidationService validationService;

    /**
     * Instantiates a new Sign in servlet.
     */
    public SignInServlet() {
        this.accountService =
                new AccountServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("signin.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {

        String email = null;
        String password = req.getParameter("password");
        String isRemember = req.getParameter("remember");
        try {
            email = validationService.getValidEmail(
                    req.getParameter("email"));
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage(), e);
            req.setAttribute("notValid", e.getMessage());
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
            return;
        }
        try {
            UserEntity user = accountService.signInUser(email, password);
            if (isRemember != null && isRemember.equals("on")) {
                Cookie cookie = ServletUtils.createCookie("userID",
                        String.valueOf(user.getId()));
                resp.addCookie(cookie);
            }
            req.getSession().setAttribute("user", user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect(resp.encodeRedirectURL("/"));
        } catch (AccountServiceException e) {
            LOGGER.warn("Login failed: "
                    + e.getMessage(), e);
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/signin.jsp")
                    .forward(req, resp);
        }
    }
}
