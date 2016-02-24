package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;

/**
 * Feature dao implementation.
 */
public class FeatureDAOImpl extends AbstractGenericDAO<FeatureEntity>
        implements FeatureDAO {

    /**
     * Instantiates a new Feature dao.
     */
    public FeatureDAOImpl() {
        super(FeatureEntity.class);
    }

}
