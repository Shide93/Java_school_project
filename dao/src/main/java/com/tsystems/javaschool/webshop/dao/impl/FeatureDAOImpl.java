package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Feature dao implementation.
 */
public class FeatureDAOImpl extends AbstractGenericDAO<FeatureEntity>
        implements FeatureDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FeatureDAOImpl.class);
    /**
     * Instantiates a new Feature dao.
     */
    public FeatureDAOImpl() {
        super(FeatureEntity.class, LOGGER);
    }

}
