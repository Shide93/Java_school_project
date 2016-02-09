package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.services.AccountService;
import com.tsystems.javaschool.webshop.services.AccountServiceImpl;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        //create user
        AccountService accountService = new AccountServiceImpl();
        accountService.createUser(email, password, name);
    }
}
