package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
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
import java.util.Map;

/**
 * Created by Shide on 28.02.2016.
 */
public class ProductBackendServlet extends HttpServlet {
    /**
     * The Product service.
     */
    private ProductService productService;
    /**
     * The Feature service.
     */
    private FeatureService featureService;
    /**
     * The Validation service.
     */
    private ValidationService validationService;
    /**
     * Instantiates a new Product backend servlet.
     */
    public ProductBackendServlet() {
        productService = new ProductServiceImpl();
        featureService = new FeatureServiceImpl();
        validationService = new ValidationServiceImpl();
    }
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        String productIdStr = req.getParameter("productId");
        //get all products
        List<ProductEntity> products = productService.getAll();
        List<FeatureEntity> features = featureService.getAll();

        if (productIdStr == null) {        //when enter page without params
            req.setAttribute("selectedProduct", products.get(0));
        } else {
            Integer productId = Integer.parseInt(productIdStr);
            if (productId > 0) {                            //when choose product in sidebar
                req.setAttribute("selectedProduct",         //0 is for new product
                        productService.get(productId));
            }
        }
        req.setAttribute("products", products);
        req.setAttribute("features", features);
        req.getRequestDispatcher("/backend/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String categoryIdStr = req.getParameter("category");
        Map<String, String[]> map = req.getParameterMap();
        String[] featureIds = req.getParameterValues("prod_features[id]");
        String[] featureValues = req.getParameterValues("prod_features[value]");


        if (action.equals("add")) {
            Integer price = Integer.parseInt(priceStr);
            Integer stock = Integer.parseInt(stockStr);
            Integer categoryId = Integer.parseInt(categoryIdStr);
            ProductEntity product= new ProductEntity();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            CategoryEntity cat = new CategoryEntity();
            cat.setId(categoryId);
            product.setCategory(cat);
            for (int i = 0; i < featureIds.length; i++) {
                Integer fId = Integer.parseInt(featureIds[i]);
                ProductFeatureEntity feature = new ProductFeatureEntity();
                feature.setProductId(product.getId());
                feature.setProduct(product);
                feature.setFeatureId(fId);
                feature.setValue(featureValues[i]);
                product.getFeatures().add(feature);
            }
            productService.add(product);
            resp.sendRedirect(resp.encodeRedirectURL(
                    "/backend/products?productId="
                            + product.getId()));
        } else if (action.equals("save")) {
            Integer id = Integer.parseInt(idStr);
            Integer price = Integer.parseInt(priceStr);
            Integer stock = Integer.parseInt(stockStr);
            Integer categoryId = Integer.parseInt(categoryIdStr);
            ProductEntity product = new ProductEntity();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            CategoryEntity cat = new CategoryEntity();
            cat.setId(categoryId);
            product.setCategory(cat);
            for (int i = 0; i < featureIds.length; i++) {
                Integer fId = Integer.parseInt(featureIds[i]);
                ProductFeatureEntity feature = new ProductFeatureEntity();
                feature.setProductId(product.getId());
                feature.setProduct(product);
                feature.setFeatureId(fId);
                feature.setValue(featureValues[i]);
                product.getFeatures().add(feature);
            }
            productService.update(product);
            resp.sendRedirect(resp.encodeRedirectURL(
                    "/backend/products?productId="
                            + product.getId()));
        } else if (action.equals("remove")) {
            Integer id = Integer.parseInt(idStr);

            productService.delete(id);
            resp.sendRedirect(resp.encodeRedirectURL("/backend/products"));
        }
    }
}
