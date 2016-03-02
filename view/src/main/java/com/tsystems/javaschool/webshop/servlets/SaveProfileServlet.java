package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * The Order service.
     */
    private OrderService orderService;

    /**
     * Instantiates a new Save profile servlet.
     */
    public SaveProfileServlet() {
        accountService =
                new AccountServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {

        //get all orders for user
        UserEntity user = (UserEntity) req.getSession().getAttribute("user");
        List<OrderEntity> orders = orderService.getAllByUser(user.getId());
        req.setAttribute("orders", orders);
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
            //TODO: logic with exceptions not good
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
        String street = req.getParameter("street");

        String buildingString = req.getParameter("building");
        String flatString = req.getParameter("flat");
        //TODO: validate
        Integer building = Integer.parseInt(buildingString);
        Integer flat = Integer.parseInt(flatString);

        UserEntity user = (UserEntity) req.getSession().getAttribute("user");
        UserEntity newUser = new UserEntity();

        newUser.setId(user.getId());
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setPhone(phone);
        newUser.setBirthDate(birthDate);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setIsAdmin(user.getIsAdmin());

        AddressEntity address = new AddressEntity();
        newUser.setAddress(address);
        if (user.getAddress() != null) {
            address.setId(user.getAddress().getId());
        }
        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        if (zip != null) {
            address.setZip(zip);
        }
        address.setStreet(street);
        address.setBuilding(building);
        address.setFlat(flat);
        if (hasErrors) {
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } else {
            newUser = accountService.saveProfile(newUser);
            req.getSession().setAttribute("user", newUser);
            req.setAttribute("profileSaved", "Profile saved!");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }
}
