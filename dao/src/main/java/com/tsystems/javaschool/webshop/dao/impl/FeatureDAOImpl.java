package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeatureEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Feature dao implementation.
 */
@Repository
public class FeatureDAOImpl extends AbstractGenericDAO<FeatureEntity>
        implements FeatureDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FeatureDAOImpl.class);

    @Override
    public final List<ProductFeatureEntity> getAllValues() {
        TypedQuery<ProductFeatureEntity> query =
                manager.createNamedQuery("ProductFeatureEntity.getAllValues",
                        ProductFeatureEntity.class);

        return query.getResultList();
    }
}
