package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
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
 * Servlet accepts sign up form data by POST method and adds user to system.
 */
public class SignUpServlet extends HttpServlet {


    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SignUpServlet.class);

    /**
     * The Account service.
     */
    private AccountService accountService;

    /**
     * Instantiates a new Sign up servlet.
     */
    public SignUpServlet() {
        this.accountService =
                new AccountServiceImpl();
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        //TODO:validate email
        //TODO:validate pass
        if (!password.equals(password2)) {
            //TODO: passwords are not equal
        }
        //create user
        try {
            UserEntity user = accountService.signUpUser(name,
                    lastName,
                    email,
                    password);

            Cookie cookie = ServletUtils.createCookie("userID",
                           String.valueOf(user.getId()));
            resp.addCookie(cookie);
            req.getSession().setAttribute("user", user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/welcome.jsp");
        } catch (AccountServiceException e) {
            LOGGER.warn("create user failed");
            // TODO: redirect to form with msg
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
