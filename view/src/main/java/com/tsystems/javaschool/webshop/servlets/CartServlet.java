package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.CartServiceImpl;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;
import flexjson.JSONSerializer;
import flexjson.transformer.StringTransformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cart servlet that manages all cart manipulations.
 */
public class CartServlet extends HttpServlet {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartServlet.class);

    /**
     * The Cart service.
     */
    private CartService cartService;
    /**
     * The Json serializer.
     */
    private JSONSerializer jsonSerializer;

    /**
     * Instantiates a new Cart servlet.
     */
    public CartServlet() {
        cartService = new CartServiceImpl();
        jsonSerializer = new JSONSerializer();
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

        String productIdStr = req.getParameter("product_id");
        String quantityStr = req.getParameter("quantity");

        createCartIfNone(req, resp);
        CartEntity cart = (CartEntity) req.getSession()
                .getAttribute("cart");


            if (req.getParameter("action").equals("add")) {
                Integer  quantity = Integer.parseInt(quantityStr);
                Integer  productId = Integer.parseInt(productIdStr);
                CartEntity newCart = null;
                try {
                    newCart = cartService.addToCart(productId, quantity, cart.getId());
                } catch (ServiceException e) {
                    LOGGER.error(e.getMessage(), e);
                    resp.getWriter().println("{\"error\": \"duplicate\"}");
                    return;
                }
                req.getSession().setAttribute("cart", newCart);
                resp.getWriter().println(jsonSerializer.serialize(newCart));
            } else if (req.getParameter("action").equals("edit")) {
                Integer  quantity = Integer.parseInt(quantityStr);
                Integer  productId = Integer.parseInt(productIdStr);
                CartEntity newCart = cartService.editCartProduct(productId, quantity, cart.getId());
                req.getSession().setAttribute("cart", newCart);
                resp.getWriter().println(jsonSerializer.deepSerialize(newCart));


            } else if (req.getParameter("action").equals("remove")) {
                    Integer  productId = Integer.parseInt(productIdStr);
                    CartEntity newCart = cartService.removeFromCart(productId, cart.getId());
                    req.getSession().setAttribute("cart", newCart);
                    resp.getWriter().println(jsonSerializer.serialize(newCart));
            }

    }

    /**
     * Checks cart for existing and creates if not exist then adds it to session.
     *
     * @param req  the request object
     * @param resp the response object
     */
    private void createCartIfNone(final HttpServletRequest req,
                                 final HttpServletResponse resp) {

        //check if cart exists in session or create it
        CartEntity cart = (CartEntity) req.getSession()
                .getAttribute("cart");

        if (cart == null) {

            cart = new CartEntity();

            cartService.add(cart);
            req.getSession().setAttribute("cart", cart);

            resp.addCookie(ServletUtils.createCookie("cartID", "" + cart.getId()));
        }
    }
}
