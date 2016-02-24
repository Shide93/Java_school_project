package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Shide on 23.02.2016.
 */
public class CategoryServlet extends HttpServlet {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductPageServlet.class);

    /**
     * The Category service.
     */
    private CategoryService categoryService;

    /**
     * Instantiates a new Category servlet.
     */
    public CategoryServlet() {
        this.categoryService =
        new CategoryServiceImpl();
    }
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        //TODO: here is code duplication, need to create Frontend Controller
        //parse request and extract categoryId
        String path = req.getRequestURI();
        LOGGER.warn(path);
        String[] tokens = path.split("/");
        LOGGER.warn(Arrays.toString(tokens));
        int categoryId = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("category")) {
                try {
                    categoryId = Integer.parseInt(tokens[i + 1]);
                    break;
                } catch (NumberFormatException e) {
                    LOGGER.warn("Entered Wrong path query", e);
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.sendRedirect("/error.jsp");
                    return;
                }
            }
        }
        //retrieve category data by id and send to view
        try {
            CategoryEntity category = categoryService.get(categoryId);
            req.setAttribute("category", category);
        } catch (ServiceException e) {
            LOGGER.error("cannot find product with id " + categoryId, e);
            resp.sendRedirect("/error.jsp");
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher("/category.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
