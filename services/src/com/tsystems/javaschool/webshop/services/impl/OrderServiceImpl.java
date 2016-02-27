package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Shide on 23.02.2016.
 */
public class OrderServiceImpl implements OrderService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDAO orderDAO;
    private ServiceHelper serviceHelper;

    public OrderServiceImpl() {
        orderDAO = new OrderDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public void add(OrderEntity order) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.create(order, manager);
        });
    }

    @Override
    public void update(OrderEntity order) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.update(order, manager);
        });
    }

    @Override
    public void delete(OrderEntity order) {
        serviceHelper.executeInTransaction(manager -> {
            orderDAO.delete(order, manager);
        });
    }

    @Override
    public OrderEntity get(int orderId) {
        return serviceHelper.load(manager -> {
            return orderDAO.getById(orderId, manager);
        });
    }

    @Override
    public List<OrderEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return orderDAO.getAll(manager);
        });
    }
}
