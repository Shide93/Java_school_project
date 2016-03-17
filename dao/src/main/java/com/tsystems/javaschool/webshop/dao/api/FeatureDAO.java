package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;

import java.util.List;

/**
 * DAO class to interact with Feature Entity.
 */
public interface FeatureDAO extends GenericDAO<Feature> {

    /**
     * Gets all values.
     *
     * @return the all values
     */
    List<ProductFeature> getAllValues();

}
