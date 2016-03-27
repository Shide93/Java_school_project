package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;

import java.util.Date;
import java.util.List;

/**
 * DAO class to interact with Order.
 */
public interface OrderDAO extends GenericDAO<Order> {

    /**
     * Gets orders by user.
     *
     * @param userId the user id
     * @return user 's orders
     */
    List<Order> getByUser(int userId);

    /**
     * count of order with status.
     *
     * @param status   the status
     * @param dateFrom the from date
     * @return count of new orders
     */
    int getOrderCountByStatus(OrderStatus status, Date dateFrom);

    /**
     * total shop sales.
     *
     * @return total sales
     */
    long totalSales();

    /**
     * Period sales.
     *
     * @param dateFrom the from date
     * @return the long
     */
    long periodSales(Date dateFrom);

    /**
     * Top of the best customers for a period.
     *
     * @param count    the count
     * @param dateFrom the from date
     * @return the list
     */
    List<User> topCustomers(int count, Date dateFrom);


    /**
     * Total orders count.
     *
     * @return total orders count
     */
    long totalOrders();
}
