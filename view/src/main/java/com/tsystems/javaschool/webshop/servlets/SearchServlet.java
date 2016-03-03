package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.impl.FeatureServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Shide on 02.03.2016.
 */
public class SearchServlet extends HttpServlet {
    private FeatureService featureService;
    private ProductService productService;
    public SearchServlet() {
        featureService = new FeatureServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
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
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String[] selectedFeatures = req.getParameterValues("features");
        List<ProductEntity> products = productService.searchByFeature(selectedFeatures);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/searchResult.jsp").forward(req, resp);
    }
}
