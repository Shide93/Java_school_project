package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;

import java.util.List;

/**
 * Service provides feature manipulation logic.
 */
public interface FeatureService extends GenericService<FeatureEntity> {

    /**
     * Gets all feature values.
     *
     * @return the all values
     */
    List<ProductFeatureEntity> getAllCategoryValues();
}
