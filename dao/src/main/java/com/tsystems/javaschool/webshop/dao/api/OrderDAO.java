package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO class to interact with OrderEntity.
 */
public interface OrderDAO extends GenericDAO<OrderEntity> {

    /**
     * Gets orders by user.
     *
     * @param userId  the user id
     * @param manager the manager
     * @return user 's orders
     */
    List<OrderEntity> getByUser(int userId, EntityManager manager);
}
