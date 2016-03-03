package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.UsersDAOImpl;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Shide on 03.03.2016.
 */
public class StatisticsServiceImpl implements StatisticsService {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AccountService.class);

    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;
    /**
     * The Order dao.
     */
    private OrderDAO orderDAO;
    /**
     * The Product dao.
     */
    private ProductDAO productDAO;
    /**
     * The Users dao.
     */
    private UsersDAO usersDAO;

    /**
     * Instantiates a new Statistics service.
     */
    public StatisticsServiceImpl() {
        serviceHelper = new ServiceHelperImpl(LOGGER);
        orderDAO = new OrderDAOImpl();
        productDAO = new ProductDAOImpl();
        usersDAO = new UsersDAOImpl();
    }

    @Override
    public final int newOrders() {
        return serviceHelper.loadInTransaction(manager ->
                orderDAO.newOrders(manager));
    }

    @Override
    public final long totalSales() {
        return serviceHelper.loadInTransaction(manager ->
                orderDAO.totalSales(manager));
    }

    @Override
    public final long monthSales() {
        return serviceHelper.loadInTransaction(manager ->
                orderDAO.monthSales(manager));
    }

    @Override
    public final List<ProductEntity> topProducts(final int count) {
        return serviceHelper.loadInTransaction(manager ->
                productDAO.topProducts(count, manager));
    }

    @Override
    public final List<UserEntity> topCustomers(final int count) {
        return serviceHelper.loadInTransaction(manager ->
                orderDAO.topCustomers(count, manager));
    }
}
