package com.tsystems.javaschool.webshop.servlets.utils;

import javax.servlet.http.Cookie;

/**
 * Provides various static util methods to servlets.
 *
 * @author Shide
 */
public final class ServletUtils {
    /**
     * Instantiates a new Servlet utils.
     */
    private ServletUtils() { }
    /**
     * Max age of cookies.
     */
    public static final int COOKIE_MAX_AGE = 60 * 2;
    /**
     * Creates a cookie with specified name and value
     * and sets is's max age from a constant.
     * @param name cookie name
     * @param value cookie value
     * @return cookie obj with specified name and value
     */
    public static Cookie createCookie(final String name, final String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }
}
