package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;

/**
 * Created by Shide on 22.02.2016.
 */
public class OrderDAOImpl extends AbstractGenericDAO<OrderEntity> implements OrderDAO {
    public OrderDAOImpl() {
        super(OrderEntity.class);
    }
}
