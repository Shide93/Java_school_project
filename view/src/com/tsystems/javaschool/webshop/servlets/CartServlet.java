package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.CartServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Shide on 23.02.2016.
 */
public class CartServlet extends HttpServlet {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CartServlet.class);

    /**
     * The Cart service.
     */
    private CartService cartService;

    /**
     * Instantiates a new Cart servlet.
     */
    public CartServlet() {
        cartService = new CartServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("action").equals("add")) {
            //check if cart exists in session or create it
            CartEntity cart = (CartEntity) req.getSession()
                    .getAttribute("cart");

            //TODO: move to function
            if (cart == null) {
                try {
                    cart = new CartEntity();
                    //TODO: cookie creation function, based on hashing  something
                    String cookieStr = "coolHashedCookie";
                    cart.setCookie(cookieStr);
                    cartService.add(cart);
                    req.getSession().setAttribute("cart", cart);
                    //TODO: cookie create function
                    Cookie cookie = new Cookie("cartID", cookieStr);
                    cookie.setMaxAge((Integer) this.getServletContext()
                            .getAttribute("USER_COOKIE_MAX_AGE"));
                    resp.addCookie(cookie);

                } catch (ServiceException e) {
                    LOGGER.error("cart add failed", e);
                    //TODO: error JSON?
                    return;
                }
            }
            String productIdStr = req.getParameter("product_id");
            String quantityStr = req.getParameter("quantity");
            Integer quantity;
            Integer productId;
            try {
                productId = Integer.parseInt(productIdStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                LOGGER.warn("invalid data format", e);
                //TODO: error JSON?
                return;
            }

            try {
                cartService.addToCart(productId, quantity, cart);

            } catch (ServiceException e) {
                LOGGER.error("add to cart failed");
                // TODO: error JSON?
            }


        } else if (req.getParameter("action").equals("edit")) {


        } else if (req.getParameter("action").equals("remove")) {


        }
    }
}
