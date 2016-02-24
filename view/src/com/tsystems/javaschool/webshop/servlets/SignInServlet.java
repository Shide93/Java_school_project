package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * Instantiates a new Sign in servlet.
     */
    public SignInServlet() {
        this.accountService =
                new AccountServiceImpl();
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String isRemember = req.getParameter("remember");
        System.out.println(email + " " + isRemember);
        try {

            UserEntity user = accountService.signInUser(email, password);
            System.out.println(user);
            if (isRemember != null && isRemember.equals("on")) {
                Cookie cookie = new Cookie("userID",
                        String.valueOf(user.getId()));
                cookie.setMaxAge((Integer) this.getServletContext()
                        .getAttribute("USER_COOKIE_MAX_AGE"));
                resp.addCookie(cookie);
            }
            req.getSession().setAttribute("user", user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect(resp.encodeRedirectURL("/"));
        } catch (ServiceException e) {
            LOGGER.warn("Login failed", e);
            // TODO: redirect to form with msg
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
