package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;

/**
 *
 */
public class FeatureDAOImpl extends AbstractGenericDAO<FeatureEntity> implements FeatureDAO {

    public FeatureDAOImpl() {
        super(FeatureEntity.class);
    }

}
