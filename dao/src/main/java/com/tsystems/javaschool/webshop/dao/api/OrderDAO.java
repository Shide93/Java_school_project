package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

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

    /**
     * count of order with NEW status.
     *
     * @param manager the manager
     * @return count of new orders
     */
    int newOrders(EntityManager manager);

    /**
     * total shop sales.
     *
     * @param manager the manager
     * @return total sales
     */
    long totalSales(EntityManager manager);

    /**
     * Month shop sales.
     *
     * @param manager the manager
     * @return month sales
     */
    long monthSales(EntityManager manager);

    /**
     * Top of the best customers.
     *
     * @param count   count of users in top
     * @param manager the manager
     * @return the list
     */
    List<UserEntity> topCustomers(int count, EntityManager manager);
}
