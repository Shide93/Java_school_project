package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.services.AccountService;
import com.tsystems.javaschool.webshop.services.AccountServiceImpl;

import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
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

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //TODO:validate email
        //TODO:validate pass

        //create user
        try {
            accountService.signUpUser(email, password);
            resp.getWriter().println("OK!");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            LOGGER.error("create user failed");
            //
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
