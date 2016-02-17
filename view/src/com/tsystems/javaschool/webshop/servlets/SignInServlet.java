package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.services.AccountService;
import com.tsystems.javaschool.webshop.services.AccountServiceImpl;
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


    private static final Logger LOGGER = LogManager.getLogger(SignInServlet.class);

    private AccountService accountService;

    public SignInServlet() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String isRemember = req.getParameter("remember");
        System.out.println(email + " " + isRemember);
        try {

            int userId = accountService.signInUser(email, password);
            if (isRemember != null && isRemember.equals("on")) {
                Cookie cookie = new Cookie("userID", String.valueOf(userId));
                cookie.setMaxAge((Integer) this.getServletContext().getAttribute("USER_COOKIE_MAX_AGE"));
                resp.addCookie(cookie);
            }
            req.getSession().setAttribute("userID", userId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/");
        } catch (ServiceException e) {
            LOGGER.warn("Login failed", e);
            // TODO: redirect to form with msg
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
