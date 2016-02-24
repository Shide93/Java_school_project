package com.tsystems.javaschool.webshop.servlets.filters;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.CartServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 * <p>
 * Checks is the user have authorized cookie and associates session with user.
 * Checks is the user have cart cookie and add cart data to session.
 */
public class CookieCheckFilter implements Filter {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CookieCheckFilter.class);
    /**
     * AccountService instance.
     */
    private AccountService accountService;
    /**
     * The Cart service.
     */
    private CartService cartService;

    @Override
    public final void init(final FilterConfig filterConfig)
            throws ServletException {
        accountService = new AccountServiceImpl();
        cartService = new CartServiceImpl();
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
        LOGGER.debug("AUTHER AAAA = "
                + ((HttpServletRequest) request).getRequestURL());

        // user authorisation flag
        boolean userFlag = false;
        // cart presence flag
        boolean cartFlag = false;

        // if user already authorised
        if (session.getAttribute("user") != null) {
           userFlag = true;
        }
        // if user already have cart
        if (session.getAttribute("cart") != null) {
            cartFlag = true;
        }
        // if user already have cart and authorized - pass through
        if (userFlag && cartFlag || cookies == null) {
            chain.doFilter(req, resp);
            return;
        }

        for (Cookie cookie : cookies) {
            //if user enters site with user cookie - add user to session
            if (cookie.getName().equals("userID") && !userFlag) {
                UserEntity user = accountService.
                        getUser(Integer.parseInt(cookie.getValue()));
                if (user != null) {
                    req.getSession().setAttribute("user", user);
                    continue;
                }
            }

            //if user enters site with cart cookie - add cart to session
            if (cookie.getName().equals("cartID") && !cartFlag) {
                try {
                    CartEntity cart = cartService.
                            getByCookie(cookie.getValue());
                    if (cart != null) {
                        req.getSession().setAttribute("cart", cart);
                    }
                } catch (ServiceException e) {
                    LOGGER.error("failed getting cart", e);
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
