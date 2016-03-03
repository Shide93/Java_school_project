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
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import com.tsystems.javaschool.webshop.services.util.ServiceLoadAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shide on 21.02.2016.
 */
public class ProductServiceImpl implements ProductService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private FeatureDAO featureDAO;
    private ServiceHelper serviceHelper;
    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
        categoryDAO = new CategoryDAOImpl();
        featureDAO = new FeatureDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public void add(ProductEntity product) {

        serviceHelper.executeInTransaction(manager -> {
            CategoryEntity category =
                categoryDAO.getById(product.getCategory().getId(), manager);
            product.setCategory(category);
            //TODO: add product features
            productDAO.create(product, manager);
        });
    }

    @Override
    public void update(ProductEntity product) {
        serviceHelper.executeInTransaction(manager -> {
            CategoryEntity category =
                categoryDAO.getById(product.getCategory().getId(), manager);
            product.setCategory(category);
            for (ProductFeatureEntity prodFeature : product.getFeatures()) {
                prodFeature.setFeature(featureDAO.getById(
                        prodFeature.getFeatureId(), manager));
            }
            //TODO: save product features
            productDAO.update(product, manager);
        });
    }

    @Override
    public void delete(Integer productId) {
        serviceHelper.executeInTransaction(manager -> {

            productDAO.delete(productId, manager);
        });
    }

    @Override
    public ProductEntity get(int productId) {
        return serviceHelper.load(manager -> {

            return productDAO.getById(productId, manager);
        });


    }

    @Override
    public List<ProductEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return productDAO.getAll(manager);
        });
    }

    @Override
    public List<ProductEntity> searchProducts(String searchQuery){
        return  serviceHelper.loadInTransaction(manager -> {

            return null;
        });
    }

    @Override
    public List<ProductEntity> searchByFeature(final String[] selectedFeatures) {
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
