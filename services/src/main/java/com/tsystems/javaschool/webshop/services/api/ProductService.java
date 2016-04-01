package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface ProductService extends GenericService<Product> {
    /**
     * Searches products that contain string in name or description.
     *
     * @param searchQuery query to search
     * @return list of products that satisfacts query
     */
    List<Product> searchProducts(String searchQuery);

    /**
     * Searches products by specified feature values.
     *
     * @param selectedFeatures array of feature values in format - id|value
     * @return the list of products
     */
    List<Product> searchByFeature(String[] selectedFeatures);

    /**
     * Add product feature product.
     *
     * @param productFeature the product feature
     * @return the product
     * @throws ServiceException the service exception
     */
    Product addNewProductFeature(ProductFeature productFeature)
            throws ServiceException;
}
