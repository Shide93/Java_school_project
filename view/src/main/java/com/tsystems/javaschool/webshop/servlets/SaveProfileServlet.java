package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.Address;
import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.OrderServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private final AccountService accountService;
    /**
     * The Order service.
     */
    private final OrderService orderService;
    /**
     * The Validation service.
     */
    private final ValidationService validationService;

    /**
     * Instantiates a new Save profile servlet.
     */
    public SaveProfileServlet() {
        accountService =
                new AccountServiceImpl();
        orderService = new OrderServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    /**
     * Instantiates a new Save profile servlet.
     *
     * @param accountSrv    the account service
     * @param orderSrv      the order service
     * @param validationSrv the validation service
     */
    public SaveProfileServlet(final AccountService accountSrv,
                              final OrderService orderSrv,
                              final ValidationService validationSrv) {
        this.accountService = accountSrv;
        this.orderService = orderSrv;
        this.validationService = validationSrv;
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {

        //get all orders for user
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.getAllByUser(user.getId());
        req.setAttribute("orders", orders);
        RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {

        String email;
        String password;
        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String phone = req.getParameter("phone");
        Date birthDate;
        String country = req.getParameter("country");
        String region = req.getParameter("region");
        String city = req.getParameter("city");
        Integer zip;
        String street = req.getParameter("street");
        Integer building;
        Integer flat;

        try {
            email = validationService.getValidEmail(req.getParameter("email"));
            if (!req.getParameter("password2").equals("")) {
                password = validationService.getValidPassword(
                        req.getParameter("password"),
                        req.getParameter("password2"));
            } else {
                password = req.getParameter("password");
            }
            birthDate = validationService.getValidDate(
                    req.getParameter("birth_date"), "dd-MM-yyyy");
            zip = validationService.getValidInt(
                    req.getParameter("zip"), "zip");
            building = validationService.getValidInt(
                    req.getParameter("building"), "building");
            flat = validationService.getValidInt(
                    req.getParameter("flat"), "flat");
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage(), e);
            req.setAttribute("notValid", e.getMessage());
            req.getRequestDispatcher("/profile.jsp")
                    .forward(req, resp);
                        return;
        }

        User user = (User) req.getSession().getAttribute("user");
        User newUser = new User();

        newUser.setId(user.getId());
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setPhone(phone);
        newUser.setBirthDate(birthDate);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setIsAdmin(user.getIsAdmin());

        Address address = new Address();
        newUser.setAddress(address);
        if (user.getAddress() != null) {
            address.setId(user.getAddress().getId());
        }
        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        address.setZip(zip);
        address.setStreet(street);
        address.setBuilding(building);
        address.setFlat(flat);

        newUser = accountService.saveProfile(newUser);
        req.getSession().setAttribute("user", newUser);
        req.setAttribute("profileSaved", "Profile saved!");
        req.getRequestDispatcher("profile.jsp").forward(req, resp);

    }
}
