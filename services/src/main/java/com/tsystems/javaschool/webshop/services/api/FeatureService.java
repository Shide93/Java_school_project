package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;

import java.util.List;

/**
 * Service provides feature manipulation logic.
 */
public interface FeatureService extends GenericService<Feature> {

    /**
     * Gets all feature values.
     *
     * @return the all values
     */
    List<ProductFeature> getAllCategoryValues();
}
