package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface ProductService extends GenericService<ProductEntity> {
    /**
     * Searches products that contain string in name or description.
     *
     * @param searchQuery query to search
     * @return list of products that satisfacts query
     */
    List<ProductEntity> searchProducts(String searchQuery);

    /**
     * Searches products by specified feature values.
     *
     * @param selectedFeatures array of feature values in format - id|value
     * @return the list of products
     */
    List<ProductEntity> searchByFeature(String[] selectedFeatures);

}
