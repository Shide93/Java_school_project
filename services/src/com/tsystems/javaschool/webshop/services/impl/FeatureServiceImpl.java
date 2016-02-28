package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.dao.impl.FeatureDAOImpl;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 *
 */
public class FeatureServiceImpl implements FeatureService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(FeatureServiceImpl.class);

    /**
     * The Feature dao.
     */
    private FeatureDAO featureDAO;
    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;

    /**
     * Instantiates a new Feature service.
     */
    public FeatureServiceImpl() {
        featureDAO = new FeatureDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }
    @Override
    public final void add(final FeatureEntity feature) {
        serviceHelper.executeInTransaction(manager -> {
            featureDAO.create(feature, manager);
        });
    }

    @Override
    public final void update(final FeatureEntity feature) {
        serviceHelper.executeInTransaction(manager -> {
            featureDAO.update(feature, manager);
        });
    }

    @Override
    public final void delete(final Integer featureId) {
        serviceHelper.executeInTransaction(manager -> {
            featureDAO.delete(featureId, manager);
        });
    }
    @Override
    public final FeatureEntity get(final int featureId) {
        return serviceHelper.loadInTransaction(manager ->
                featureDAO.getById(featureId, manager));
    }
    @Override
    public final List<FeatureEntity> getAll() {
        return serviceHelper.loadInTransaction(manager ->
                featureDAO.getAll(manager));
    }
}
