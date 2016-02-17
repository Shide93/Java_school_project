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
 * Servlet accepts sign up form data by POST method and adds user to system.
 */
public class SignUpServlet extends HttpServlet {


    private static final Logger LOGGER = LogManager.getLogger(SignUpServlet.class);

    private AccountService accountService;

    public SignUpServlet() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        //TODO:validate email
        //TODO:validate pass

        //create user
        try {
            int userId = accountService.signUpUser(name, lastName, email, password);

            Cookie cookie = new Cookie("userID", String.valueOf(userId));
            cookie.setMaxAge((Integer) this.getServletContext().getAttribute("USER_COOKIE_MAX_AGE"));
            resp.addCookie(cookie);
            req.getSession().setAttribute("userID", userId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/welcome.jsp");
        } catch (ServiceException e) {
            LOGGER.warn("create user failed");
            // TODO: redirect to form with msg
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
