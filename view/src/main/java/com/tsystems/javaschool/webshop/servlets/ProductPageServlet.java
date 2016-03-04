package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.ProductServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Shide on 22.02.2016.
 */
public class ProductPageServlet extends HttpServlet {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductPageServlet.class);

    /**
     * The Product service.
     */
    private final ProductService productService;
    /**
     * The Validation service.
     */
    private ValidationService validationService;

    /**
     * Instantiates a new Product page servlet.
     */
    public ProductPageServlet() {
        this.productService =
                new ProductServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {

        //parse request and extract productId
        String path = req.getRequestURI();
        LOGGER.warn(path);
        String[] tokens = path.split("/");
        LOGGER.warn(Arrays.toString(tokens));
        int productId = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("product")) {
                try {
                    productId = Integer.parseInt(tokens[i + 1]);
                    break;
                } catch (NumberFormatException e) {
                    LOGGER.warn("Entered rong path query", e);
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.sendRedirect("/error.jsp");
                    return;
                }
            }
        }
        //retrieve product data by id and send to view
        LOGGER.info("asdasdsad " + productId);

        ProductEntity product = productService.get(productId);
        req.setAttribute("product", product);

        RequestDispatcher rd = req.getRequestDispatcher("/product.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
