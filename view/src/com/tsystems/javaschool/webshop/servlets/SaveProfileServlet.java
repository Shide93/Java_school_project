package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.AccountService;
import com.tsystems.javaschool.webshop.services.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Saves user profile.
 */
public class SaveProfileServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(SaveProfileServlet.class);

    private AccountService accountService;

    public SaveProfileServlet() {
        accountService = new AccountServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean hasErrors = false;

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (!password2.equals("") && !password.equals(password2)) {
            LOGGER.info("passwords are not equal"); // TODO: am i need log this?
            req.setAttribute("passNotEq", "Passwords are not equal");
            hasErrors = true;
        }
        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String phone = req.getParameter("phone");

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate = null;
        try {
            birthDate = format.parse(req.getParameter("birth_date"));
        } catch (ParseException e) {
            //wrong date
            LOGGER.info("user entered wrong date"); // TODO: am i need log this?
            req.setAttribute("wrongBirth", "Wrong birth date format");
            hasErrors = true;
        }

        String country = req.getParameter("country");
        String region = req.getParameter("region");
        String city = req.getParameter("city");

        String zipString = req.getParameter("zip");

        Integer zip = null;
        try {
            if (zipString != null) {
                zip = Integer.parseInt(zipString);
            }
        } catch (NumberFormatException e) {
            //wrong zip
            LOGGER.info("user entered wrong zip"); // TODO: am i need log this?
            req.setAttribute("wrongZip", "Wrong zip code format");
            hasErrors = true;
        }
        String addr = req.getParameter("address");

        UserEntity oldUser = (UserEntity) req.getSession().getAttribute("user");
        if (hasErrors) {
            RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
            rd.forward(req, resp);
        } else {
            try {
                UserEntity newUser = accountService.saveProfile(email, password, name, lastName, phone,
                        birthDate, country, region, city, zip, addr, oldUser);
                req.getSession().setAttribute("user", newUser);
                req.setAttribute("profileSaved", "Profile saved!");
                RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
                rd.forward(req, resp);
            } catch (ServiceException e) {
                LOGGER.error("Saving error");
            }
        }
    }
}
