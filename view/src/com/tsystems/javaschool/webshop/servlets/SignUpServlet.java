package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.services.AccountService;
import com.tsystems.javaschool.webshop.services.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shide on 08.02.2016.
 */
@WebServlet(value = "/signup")
public class SignUpServlet extends HttpServlet {

    private AccountService accountService;

    public SignUpServlet() {
        this.accountService = new AccountServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");


        //create user
        try {
            accountService.createUser(email, password);
            resp.getWriter().println("OK!");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            //log
            //
            resp.getWriter().println("ERROR! " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
