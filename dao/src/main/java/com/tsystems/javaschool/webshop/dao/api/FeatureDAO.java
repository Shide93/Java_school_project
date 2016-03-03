package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO class to interact with Feature Entity.
 */
public interface FeatureDAO extends GenericDAO<FeatureEntity> {

    /**
     * Gets all values.
     *
     * @param manager the manager
     * @return the all values
     */
    List<ProductFeatureEntity> getAllValues(EntityManager manager);

}
