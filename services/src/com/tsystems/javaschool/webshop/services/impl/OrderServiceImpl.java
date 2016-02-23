package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;

import java.util.List;

/**
 * Created by Shide on 23.02.2016.
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private ServiceHelper serviceHelper;

    public OrderServiceImpl() {
        orderDAO = new OrderDAOImpl();
        serviceHelper = new ServiceHelperImpl();
    }

    @Override
    public void add(OrderEntity order) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            orderDAO.create(order, manager);
        });
    }

    @Override
    public void update(OrderEntity order) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            orderDAO.update(order, manager);
        });
    }

    @Override
    public void delete(OrderEntity order) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            orderDAO.delete(order, manager);
        });
    }

    @Override
    public OrderEntity get(int orderId) throws ServiceException {
        return serviceHelper.load(manager -> {
            return orderDAO.getById(orderId, manager);
        });
    }

    @Override
    public List<OrderEntity> getAll() throws ServiceException {
        return serviceHelper.loadTransactionally(manager -> {

            return orderDAO.getAll(manager);
        });
    }
}
