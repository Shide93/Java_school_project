package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.PaymentEntity;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.CheckoutService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.CheckoutServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Checkout servlet.
 */
public class CheckoutServlet extends HttpServlet {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SaveProfileServlet.class);
    /**
     * The Checkout service.
     */
    private CheckoutService checkoutService;
    /**
     * The Account service.
     */
    private AccountService accountService;
    /**
     * The Validation service.
     */
    private ValidationService validationService;

    /**
     * Instantiates a new Checkout servlet.
     */
    public CheckoutServlet() {
        this.checkoutService = new CheckoutServiceImpl();
        this.accountService = new AccountServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {

        List<PaymentEntity> paymentTypes = checkoutService.getPaymentTypes();
        List<ShippingEntity> shippingTypes = checkoutService.getShippingTypes();
        req.setAttribute("paymentTypes", paymentTypes);
        req.setAttribute("shippingTypes", shippingTypes);

        RequestDispatcher rd = req.getRequestDispatcher("checkout.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        //contact information params
        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String phone = req.getParameter("phone");
        //address
        String country = req.getParameter("country");
        String region = req.getParameter("region");
        String city = req.getParameter("city");
        Integer zip;
        String street = req.getParameter("street");
        Integer building;
        Integer flat;
        //shipping
        Integer shippingId;
        //payment
        Integer paymentId;
        //comment
        String comment = req.getParameter("comment");

        try {
            zip = validationService.getValidInt(
                    req.getParameter("zip"), "zip");
            building = validationService.getValidInt(
                    req.getParameter("building"), "building");
            flat = validationService.getValidInt(
                    req.getParameter("flat"), "flat");
            shippingId = validationService.getValidInt(
                    req.getParameter("shipping_id"), "shipping_id");
            paymentId = validationService.getValidInt(
                    req.getParameter("payment_id"), "payment_id");
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage(), e);
            req.setAttribute("notValid", e.getMessage());
            req.getRequestDispatcher("/checkout.jsp")
                    .forward(req, resp);
            return;
        }
        //save user info to profile
        UserEntity user = (UserEntity) req.getSession().getAttribute("user");
        UserEntity newUser = new UserEntity();

        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setIsAdmin(user.getIsAdmin());
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setPhone(phone);

        AddressEntity address = new AddressEntity();

        if (user.getAddress() != null) {
            address.setId(user.getAddress().getId());
        }
        newUser.setAddress(address);
        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        address.setZip(zip);
        address.setStreet(street);
        address.setBuilding(building);
        address.setFlat(flat);

        newUser = accountService.saveProfile(newUser);
        req.getSession().setAttribute("user", newUser);

        //create order
        CartEntity cart = (CartEntity) req.getSession().getAttribute("cart");

        checkoutService.createOrder(newUser, address, cart,
                                    paymentId, shippingId, comment);
        req.getSession().removeAttribute("cart");
        RequestDispatcher rd = req.getRequestDispatcher("order_created.jsp");
        rd.forward(req, resp);
    }
}
