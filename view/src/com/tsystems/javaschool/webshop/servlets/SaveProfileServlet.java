package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
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

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SaveProfileServlet.class);

    /**
     * The Account service.
     */
    private AccountService accountService;

    /**
     * Instantiates a new Save profile servlet.
     */
    public SaveProfileServlet() {
        accountService =
                new AccountServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {

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

        UserEntity user = (UserEntity) req.getSession().getAttribute("user");

        user.setName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsAdmin(false);
        AddressEntity address = user.getAddress();
        if (address != null) {
            address.setCountry(country);
            address.setRegion(region);
            address.setCity(city);
            if (zip != null) {
                address.setZip(zip);
            }
            //TODO: remake address
            address.setStreet(addr);
            address.setBuilding(1);
            address.setFlat(1);
        }

        if (hasErrors) {
            RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
            rd.forward(req, resp);
        } else {
            try {
                //TODO: change to updating current user in session, and JPA.refresh it if fails
                UserEntity newUser = accountService.saveProfile(user);
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
