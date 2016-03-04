package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Override
    public final List<ProductFeatureEntity> getAllValues(
            final EntityManager manager) {
        TypedQuery<ProductFeatureEntity> query =
                manager.createNamedQuery("ProductFeatureEntity.getAllValues",
                        ProductFeatureEntity.class);

        return query.getResultList();
    }
}
