package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;

/**
 * Order dao implementation.
 */
public class OrderDAOImpl extends AbstractGenericDAO<OrderEntity> implements OrderDAO {
    /**
     * Instantiates a new Order dao.
     */
    public OrderDAOImpl() {
        super(OrderEntity.class);
    }
}
