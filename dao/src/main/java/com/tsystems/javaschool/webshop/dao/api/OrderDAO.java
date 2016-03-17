package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.User;

import java.util.List;

/**
 * DAO class to interact with Order.
 */
public interface OrderDAO extends GenericDAO<Order> {

    /**
     * Gets orders by user.
     *
     * @param userId  the user id
     * @return user 's orders
     */
    List<Order> getByUser(int userId);

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
    List<User> topCustomers(int count);
}
