package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shide on 03.03.2016.
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AccountService.class);
    /**
     * The Order dao.
     */
    @Autowired
    private OrderDAO orderDAO;
    /**
     * The Product dao.
     */
    @Autowired
    private ProductDAO productDAO;
    /**
     * The Users dao.
     */
    @Autowired
    private UsersDAO usersDAO;

    @Override
    public final int newOrders() {
        return orderDAO.newOrders();
    }

    @Override
    public final long totalSales() {
        return orderDAO.totalSales();
    }

    @Override
    public final long monthSales() {
        return orderDAO.monthSales();
    }

    @Override
    public final List<Product> topProducts(final int count) {
        return productDAO.topProducts(count);
    }

    @Override
    public final List<User> topCustomers(final int count) {
        return orderDAO.topCustomers(count);
    }
}
