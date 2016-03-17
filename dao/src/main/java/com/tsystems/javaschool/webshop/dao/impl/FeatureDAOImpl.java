package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Feature dao implementation.
 */
@Repository
public class FeatureDAOImpl extends AbstractGenericDAO<Feature>
        implements FeatureDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FeatureDAOImpl.class);

    @Override
    public final List<ProductFeature> getAllValues() {
        TypedQuery<ProductFeature> query =
                manager.createNamedQuery("ProductFeatureEntity.getAllValues",
                        ProductFeature.class);

        return query.getResultList();
    }
}
