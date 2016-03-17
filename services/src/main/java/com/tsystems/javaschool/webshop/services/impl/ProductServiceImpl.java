package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Product service.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductServiceImpl.class);
    /**
     * The Product dao.
     */
    @Autowired
    private ProductDAO productDAO;
    /**
     * The Category dao.
     */
    @Autowired
    private CategoryDAO categoryDAO;
    /**
     * The Feature dao.
     */
    @Autowired
    private FeatureDAO featureDAO;

    @Override
    public final void add(final Product product) {


        Category category =
                categoryDAO.getById(product.getCategory().getId());
        product.setCategory(category);
        productDAO.create(product);

    }

    @Override
    public final void update(final Product product) {

        Category category =
                categoryDAO.getById(product.getCategory().getId());
        product.setCategory(category);
        for (ProductFeature prodFeature : product.getFeatures()) {
            prodFeature.setFeature(featureDAO.getById(
                    prodFeature.getFeatureId()));
        }
        productDAO.update(product);

    }

    @Override
    public final void delete(final Integer productId) {


        productDAO.delete(productId);

    }

    @Override
    public final Product get(final int productId) {


        return productDAO.getById(productId);



    }

    @Override
    public final List<Product> getAll() {


        return productDAO.getAll();
    }

    @Override
    public final List<Product> searchProducts(final String searchQuery) {


        return null;

    }

    @Override
    public final List<Product> searchByFeature(
            final String[] selectedFeatures) {

        Map<Integer, List<String>> map = new HashMap<>();

        for (final String selectedFeature : selectedFeatures) {
            String[] splFeature = selectedFeature.split("/");
            Integer featureId = Integer.parseInt(splFeature[0]);
            String featureVal = splFeature[1];
            if (!map.containsKey(featureId)) {
                map.put(featureId, new ArrayList<>());
            }
            map.get(featureId).add(featureVal);
        }
        return productDAO.findByFeatures(map);
    }
}
