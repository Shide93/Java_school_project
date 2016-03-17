package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;

import java.util.List;

/**
 * Service provides order manipulation logic.
 */
public interface OrderService extends GenericService<Order> {

    /**
     * Change status of an order.
     *
     * @param id     order id
     * @param status new order status
     */
    void changeStatus(int id, OrderStatus status);

    /**
     * Gets all user's orders.
     *
     * @param userId the user id
     * @return user's orders
     */
    List<Order> getAllByUser(int userId);

}
