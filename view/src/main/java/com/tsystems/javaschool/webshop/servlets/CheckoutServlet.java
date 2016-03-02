package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.PaymentEntity;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.CheckoutService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.CheckoutServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shide on 27.02.2016.
 */
public class CheckoutServlet extends HttpServlet {
    private CheckoutService checkoutService;
    private AccountService accountService;

    public CheckoutServlet() {
        this.checkoutService = new CheckoutServiceImpl();
        this.accountService = new AccountServiceImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
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
    protected void doPost(final HttpServletRequest req,
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
        String zipString = req.getParameter("zip");
        Integer zip = Integer.parseInt(zipString);
        String street = req.getParameter("street");
        String buildingString = req.getParameter("building");
        String flatString = req.getParameter("flat");
        //TODO: validate
        Integer building = Integer.parseInt(buildingString);
        Integer flat = Integer.parseInt(flatString);
        //shipping
        String shippingIdString = req.getParameter("shipping_id");
        //TODO: validate
        Integer shippingId = Integer.parseInt(shippingIdString);
        //payment
        String paymentIdString = req.getParameter("payment_id");
        //TODO: validate
        Integer paymentId = Integer.parseInt(paymentIdString);
        //comment
        String comment = req.getParameter("comment");

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
