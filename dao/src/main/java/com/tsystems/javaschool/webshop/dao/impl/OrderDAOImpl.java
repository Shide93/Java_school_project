package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Order dao implementation.
 */
public class OrderDAOImpl extends AbstractGenericDAO<OrderEntity>
        implements OrderDAO {
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

    @Override
    public final List<OrderEntity> getByUser(final int userId,
                                             final EntityManager manager) {
        TypedQuery<OrderEntity> q =
            manager.createNamedQuery("OrderEntity.getByUser",
                    OrderEntity.class);
        q.setParameter("id", userId);
        return q.getResultList();
    }
}
