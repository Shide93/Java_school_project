package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
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
    public final void add(final OrderEntity order) {
        orderDAO.create(order);
    }

    @Override
    public final void update(final OrderEntity order) {
        orderDAO.update(order);
    }

    @Override
    public final void delete(final Integer orderId) {
        orderDAO.delete(orderId);
    }

    @Override
    public final OrderEntity get(final int orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public final List<OrderEntity> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public final void changeStatus(final int id, final OrderStatus status) {
        OrderEntity order = orderDAO.getById(id);
        order.setOrderStatus(status);
    }

    @Override
    public final List<OrderEntity> getAllByUser(final int userId) {
       return orderDAO.getByUser(userId);
    }
}
