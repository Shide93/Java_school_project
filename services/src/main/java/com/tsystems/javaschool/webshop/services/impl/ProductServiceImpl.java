package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        productDAO.create(product);
    }

    @Override
    public final void update(final Product product) {

        Category category =
                categoryDAO.getById(product.getCategory().getId());
        product.setCategory(category);

        //removes productFeatures with null or empty value
        Iterator<ProductFeature> iterator = product.getFeatures().iterator();
        while (iterator.hasNext()) {
            ProductFeature productFeature = iterator.next();
            if (productFeature.getValue() == null
                    || productFeature.getValue().isEmpty()) {
                iterator.remove();
            }
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
    public final List<Product> searchByFeature(
            final String[] selectedFeatures) {

        Map<Integer, List<String>> map = new HashMap<>();
        if (selectedFeatures != null) {
            for (final String selectedFeature : selectedFeatures) {
                String[] splFeature = selectedFeature.split("/");
                Integer featureId = Integer.parseInt(splFeature[0]);
                String featureVal = splFeature[1];
                if (!map.containsKey(featureId)) {
                    map.put(featureId, new ArrayList<>());
                }
                map.get(featureId).add(featureVal);
            }
        }
        return productDAO.findByFeatures(map);
    }

    @Override
    public final Product addNewProductFeature(
            final ProductFeature productFeature)
            throws ServiceException {

        Product product = productDAO.getById(productFeature.getProductId());
        if (product == null) {
            throw new ServiceException("Product doesn't exists");
        }

        Feature feature = featureDAO.getById(productFeature.getFeatureId());
        if (feature == null) {
            throw new ServiceException("Feature doesn't exists");
        }

        if (productFeature.getValue() == null
                || productFeature.getValue().isEmpty()) {
            throw new ServiceException("Feature value is empty");
        }

        productFeature.setFeature(feature);
        productFeature.setProduct(product);

        if (product.getFeatures().contains(productFeature)) {
            throw new ServiceException("Feature value already assigned");
        }
        product.getFeatures().add(productFeature);
        productDAO.update(product);
        return product;
    }

    /**
     * Sets product dao.
     *
     * @param dao injecting dao
     */
    public final void setProductDAO(final ProductDAO dao) {
        this.productDAO = dao;
    }

    /**
     * Sets category dao.
     *
     * @param dao injecting dao
     */
    public final void setCategoryDAO(final CategoryDAO dao) {
        this.categoryDAO = dao;
    }

    /**
     * Sets feature dao.
     *
     * @param dao injecting dao
     */
    public final void setFeatureDAO(final FeatureDAO dao) {
        this.featureDAO = dao;
    }
}
