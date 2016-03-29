package com.tsystems.javaschool.webshop.controllers.interceptors;

import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.services.api.CartService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Shide on 25.03.2016.
 */
public class CartCookieInterceptor extends HandlerInterceptorAdapter {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartCookieInterceptor.class);
    /**
     * The Cart service.
     */
    @Autowired
    private CartService cartService;
    @Override
    public final boolean preHandle(final HttpServletRequest req,
                                   final HttpServletResponse resp,
                                   final Object handler) throws Exception {

        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        LOGGER.debug("interceptor works!");
        // if user already have cart
        if (session.getAttribute("cart") != null) {
            return true;
        }

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //if user enters site with cart cookie - add cart to session
                if (cookie.getName().equals("cartID")) {
                    Cart cart = cartService.
                            get(Integer.parseInt(cookie.getValue()));
                    if (cart != null) {
                        req.getSession().setAttribute("cart", cart);
                        LOGGER.debug("cart with id = "
                                + cart.getId() + " is set to session");
                    }
                }
            }
        }
        return true;
    }
}
