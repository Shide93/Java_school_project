package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

import java.util.List;

/**
 * DAO class to interact with OrderEntity.
 */
public interface OrderDAO extends GenericDAO<OrderEntity> {

    /**
     * Gets orders by user.
     *
     * @param userId  the user id
     * @return user 's orders
     */
    List<OrderEntity> getByUser(int userId);

    /**
     * count of order with NEW status.
     *
     * @return count of new orders
     */
    int newOrders();

    /**
     * total shop sales.
     *
     * @return total sales
     */
    long totalSales();

    /**
     * Month shop sales.
     *
     * @return month sales
     */
    long monthSales();

    /**
     * Top of the best customers.
     *
     * @param count   count of users in top
     * @return the list
     */
    List<UserEntity> topCustomers(int count);
}
