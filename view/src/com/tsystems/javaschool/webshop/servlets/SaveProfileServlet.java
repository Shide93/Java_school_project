package com.tsystems.javaschool.webshop.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Saves user profile.
 */
public class SaveProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String oldPassword = req.getParameter("old_password");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

      //  Date birthDate = format.parse(req.getParameter("birth_date"));


        String country = req.getParameter("country");
        String region = req.getParameter("region");
        String city = req.getParameter("city");
        Integer zip = Integer.parseInt(req.getParameter("zip"));
        String address = req.getParameter("address");

    }
}
