package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Order dao implementation.
 */
public class OrderDAOImpl extends AbstractGenericDAO<OrderEntity> implements OrderDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderDAOImpl.class);
    /**
     * Instantiates a new Order dao.
     */
    public OrderDAOImpl() {
        super(OrderEntity.class, LOGGER);
    }
}
