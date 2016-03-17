package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Feature service.
 */
@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FeatureServiceImpl.class);

    /**
     * The Feature dao.
     */
    @Autowired
    private FeatureDAO featureDAO;

    @Override
    public final void add(final Feature feature) {

        featureDAO.create(feature);
    }

    @Override
    public final void update(final Feature feature) {
        featureDAO.update(feature);
    }

    @Override
    public final void delete(final Integer featureId) {

        featureDAO.delete(featureId);

    }
    @Override
    public final Feature get(final int featureId) {
        return featureDAO.getById(featureId);
    }
    @Override
    public final List<Feature> getAll() {
        return featureDAO.getAll();
    }

    @Override
    public final List<ProductFeature> getAllCategoryValues() {
        return featureDAO.getAllValues();
    }
}
