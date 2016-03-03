package com.tsystems.javaschool.webshop.servlets.utils;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import java.util.List;

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
    public static final int COOKIE_MAX_AGE = 60 * 60;

    /**
     * Creates a cookie with specified name and value
     * and sets is's max age from a constant.
     *
     * @param name  cookie name
     * @param value cookie value
     * @return cookie obj with specified name and value
     */
    public static Cookie createCookie(final String name, final String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }


    /**
     * Sets category list to context.
     * Used to show category list in sidebar.
     *
     * @param context Servlet context of application
     */
    public static void setCategoryListToContext(final ServletContext context) {
        CategoryService categoryService = new CategoryServiceImpl();
        List<CategoryEntity> categories = categoryService.getAllIdNames();
        context.setAttribute("categoryList", categories);
    }
}
