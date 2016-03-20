package com.tsystems.javaschool.webshop.servlets.filters;

import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.CartService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet filter that checks user cookies
 * and writes to session information about user.
 * <p/>
 * Checks is the user have authorized cookie and associates session with user.
 * Checks is the user have cart cookie and add cart data to session.
 */
@Component
public class CookieCheckFilter implements Filter {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CookieCheckFilter.class);
    /**
     * AccountService instance.
     */
    @Autowired
    private AccountService accountService;
    /**
     * The Cart service.
     */
    @Autowired
    private CartService cartService;

    @Override
    public final void init(final FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public final void doFilter(final ServletRequest request,
                               final ServletResponse response,
                               final FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();

        // cart presence flag
        boolean cartFlag = false;

        // if user already have cart
        if (session.getAttribute("cart") != null) {
            chain.doFilter(req, resp);
            return;
        }

        for (Cookie cookie : cookies) {

            //if user enters site with cart cookie - add cart to session
            if (cookie.getName().equals("cartID")) {
                Cart cart = cartService.
                        get(Integer.parseInt(cookie.getValue()));
                if (cart != null) {
                    req.getSession().setAttribute("cart", cart);
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
