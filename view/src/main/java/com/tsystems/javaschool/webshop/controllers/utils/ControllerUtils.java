package com.tsystems.javaschool.webshop.controllers.utils;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides various static util methods to servlets.
 *
 * @author Shide
 */
public final class ControllerUtils {
    /**
     * Instantiates a new Servlet utils.
     */
    private ControllerUtils() { }

    /**
     * Max age of cookies.
     */
    public static final int COOKIE_MAX_AGE = 2 * 60 * 60;

    /**
     * The constant TOKEN_EXPIRATION_TIME.
     */
    public static final long TOKEN_EXPIRATION_TIME = 5 * 60 * 1000L;

    /**
     * Creates a cookie with specified name and value
     * and sets is's max age from a constant.
     *
     * @param name  cookie name
     * @param value cookie value
     * @return cookie obj with specified name and value
     */
    public static Cookie createCookie(final String name,
                                      final String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }

    /**
     * Sets token to context.
     *
     * @param token   the token
     * @param context the context
     */
    public static void setTokenToContext(final String token,
                                         final ServletContext context) {
        @SuppressWarnings("unchecked")
        Map<String, Long> tokenMap
                = (Map<String, Long>) context.getAttribute("tokenMap");
        if (tokenMap == null) {
            tokenMap = new HashMap<>();
            context.setAttribute("tokenMap", tokenMap);
        }
        long expirationTime = System.currentTimeMillis() + TOKEN_EXPIRATION_TIME;
        tokenMap.put(token, expirationTime);
    }

    /**
     * Checks is token exists in context.
     *
     * @param token   the token
     * @param context the context
     * @return the boolean
     */
    public static boolean isTokenExists(final String token,
                                        final ServletContext context) {
        @SuppressWarnings("unchecked")
        Map<String, Long> tokenMap
                = (Map<String, Long>) context.getAttribute("tokenMap");
        if (tokenMap == null) {
          return false;
        }
        if (tokenMap.containsKey(token)
                && tokenMap.get(token) >= System.currentTimeMillis()) {
            tokenMap.remove(token);
            return true;
        }
        return false;
    }
}
