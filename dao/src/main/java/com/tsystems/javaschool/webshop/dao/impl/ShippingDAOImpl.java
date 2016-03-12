package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The type Shipping dao.
 */
public class ShippingDAOImpl extends AbstractGenericDAO<ShippingEntity>
        implements ShippingDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);
}

