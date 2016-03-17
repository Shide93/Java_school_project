package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Order service.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderServiceImpl.class);

    /**
     * The Order dao.
     */
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public final void add(final Order order) {
        orderDAO.create(order);
    }

    @Override
    public final void update(final Order order) {
        orderDAO.update(order);
    }

    @Override
    public final void delete(final Integer orderId) {
        orderDAO.delete(orderId);
    }

    @Override
    public final Order get(final int orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public final List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public final void changeStatus(final int id, final OrderStatus status) {
        Order order = orderDAO.getById(id);
        order.setOrderStatus(status);
    }

    @Override
    public final List<Order> getAllByUser(final int userId) {
       return orderDAO.getByUser(userId);
    }
}
