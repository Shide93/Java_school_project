package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Order service.
 */
public class OrderServiceImpl implements OrderService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderServiceImpl.class);

    /**
     * The Order dao.
     */
    private final OrderDAO orderDAO;
    /**
     * The Service helper.
     */
    private final ServiceHelper serviceHelper;

    /**
     * Instantiates a new Order service.
     */
    public OrderServiceImpl() {
        orderDAO = new OrderDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    /**
     * Instantiates a new Order service.
     *
     * @param orderDAO      the order dao
     * @param serviceHelper the service helper
     */
    public OrderServiceImpl(final OrderDAO orderDAO,
                            final ServiceHelper serviceHelper) {
        this.orderDAO = orderDAO;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public final void add(final OrderEntity order) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.create(order, manager);
        });
    }

    @Override
    public final void update(final OrderEntity order) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.update(order, manager);
        });
    }

    @Override
    public final void delete(final Integer orderId) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.delete(orderId, manager);
        });
    }

    @Override
    public final OrderEntity get(final int orderId) {
        return serviceHelper.load(manager -> {
            return orderDAO.getById(orderId, manager);
        });
    }

    @Override
    public final List<OrderEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return orderDAO.getAll(manager);
        });
    }

    @Override
    public final void changeStatus(final int id, final OrderStatus status) {
        serviceHelper.executeInTransaction(manager -> {
            OrderEntity order = orderDAO.getById(id, manager);
            order.setOrderStatus(status);
        });
    }

    @Override
    public final List<OrderEntity> getAllByUser(final int userId) {
        return serviceHelper.loadInTransaction(manager ->
                orderDAO.getByUser(userId, manager));
    }
}
