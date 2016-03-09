package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;
import com.tsystems.javaschool.webshop.dao.impl.CategoryDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.FeatureDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Product service.
 */
public class ProductServiceImpl implements ProductService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductServiceImpl.class);
    /**
     * The Product dao.
     */
    private final ProductDAO productDAO;
    /**
     * The Category dao.
     */
    private final CategoryDAO categoryDAO;
    /**
     * The Feature dao.
     */
    private final FeatureDAO featureDAO;
    /**
     * The Service helper.
     */
    private final ServiceHelper serviceHelper;

    /**
     * Instantiates a new Product service.
     */
    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
        categoryDAO = new CategoryDAOImpl();
        featureDAO = new FeatureDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    /**
     * Instantiates a new Product service.
     *
     * @param productDAO    the product dao
     * @param categoryDAO   the category dao
     * @param featureDAO    the feature dao
     * @param serviceHelper the service helper
     */
    public ProductServiceImpl(final ProductDAO productDAO,
                              final CategoryDAO categoryDAO,
                              final FeatureDAO featureDAO,
                              final ServiceHelper serviceHelper) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
        this.featureDAO = featureDAO;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public final void add(final ProductEntity product) {

        serviceHelper.executeInTransaction(manager -> {
            CategoryEntity category =
                categoryDAO.getById(product.getCategory().getId(), manager);
            product.setCategory(category);
            productDAO.create(product, manager);
        });
    }

    @Override
    public final void update(final ProductEntity product) {
        serviceHelper.executeInTransaction(manager -> {
            CategoryEntity category =
                categoryDAO.getById(product.getCategory().getId(), manager);
            product.setCategory(category);
            for (ProductFeatureEntity prodFeature : product.getFeatures()) {
                prodFeature.setFeature(featureDAO.getById(
                        prodFeature.getFeatureId(), manager));
            }
            productDAO.update(product, manager);
        });
    }

    @Override
    public final void delete(final Integer productId) {
        serviceHelper.executeInTransaction(manager -> {

            productDAO.delete(productId, manager);
        });
    }

    @Override
    public final ProductEntity get(final int productId) {
        return serviceHelper.load(manager -> {

            return productDAO.getById(productId, manager);
        });


    }

    @Override
    public final List<ProductEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return productDAO.getAll(manager);
        });
    }

    @Override
    public final List<ProductEntity> searchProducts(final String searchQuery) {
        return  serviceHelper.loadInTransaction(manager -> {

            return null;
        });
    }

    @Override
    public final List<ProductEntity> searchByFeature(
            final String[] selectedFeatures) {
        return  serviceHelper.loadInTransaction(manager -> {
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
            return productDAO.findByFeatures(map, manager);
        });
    }
}
