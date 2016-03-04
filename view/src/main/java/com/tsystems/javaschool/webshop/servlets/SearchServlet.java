package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.FeatureServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ProductServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shide on 02.03.2016.
 */
public class SearchServlet extends HttpServlet {
    /**
     * The Feature service.
     */
    private FeatureService featureService;
    /**
     * The Product service.
     */
    private ProductService productService;
    /**
     * The Validation service.
     */
    private ValidationService validationService;

    /**
     * Instantiates a new Search servlet.
     */
    public SearchServlet() {
        featureService = new FeatureServiceImpl();
        productService = new ProductServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {

        //features for filter
        List<FeatureEntity> features = featureService.getAll();
        List<ProductFeatureEntity> featureValues =
                featureService.getAllCategoryValues();
        req.setAttribute("features", features);
        req.setAttribute("featureValues", featureValues);
        req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String[] selectedFeatures = req.getParameterValues("features");
        List<ProductEntity> products =
                productService.searchByFeature(selectedFeatures);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/searchResult.jsp").forward(req, resp);
    }
}
