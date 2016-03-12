package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * The type Shipping dao.
 */
@Repository
public class ShippingDAOImpl extends AbstractGenericDAO<ShippingEntity>
        implements ShippingDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);
}

