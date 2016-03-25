package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.dto.ProductDTO;
import com.tsystems.javaschool.webshop.dao.entities.dto.StatisticsDTO;
import com.tsystems.javaschool.webshop.dao.entities.dto.UserDTO;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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
        return orderDAO.getOrderCountByStatus(OrderStatus.NEW);
    }

    @Override
    public final long totalSales() {
        return orderDAO.totalSales();
    }

    @Override
    public final long periodSales(final int period) {
        return orderDAO.periodSales(period);
    }

    @Override
    public final List<Product> topProducts(final int count) {
        return productDAO.topProducts(count);
    }

    @Override
    public final List<User> topCustomers(final int count) {
        return orderDAO.topCustomers(count);
    }

    @Override
    public final StatisticsDTO getShopReport(final String period,
                                       final Integer topProductsCount,
                                       final Integer topUsersCount,
                                       final Integer minStock) {
        StatisticsDTO report = new StatisticsDTO();

        report.setTotalSales(totalSales());
        int periodValue = getCalendarPeriodValue(period);
        if (periodValue < 0) {
            throw new IllegalArgumentException("wrong period value");
        }
        report.setPeriodSales(orderDAO.periodSales(periodValue));

        report.setTotalOrders(orderDAO.getAll().size());

        for (OrderStatus status : OrderStatus.values()) {
            report.getOrdersPerStatus().put(status,
                    orderDAO.getOrderCountByStatus(status));
        }

        for (User user : orderDAO.topCustomers(topUsersCount)) {
            report.getTopUsers().add(new UserDTO(user));
        }

        for (Product product : productDAO.topProducts(topProductsCount)) {
            report.getTopProducts().add(new ProductDTO(product));
        }

        for (Product product : productDAO.getOutOfStockProducts(minStock)) {
            report.getOutOfStock().add(new ProductDTO(product));
        }

        return report;
    }

    /**
     * Gets calendar period value from string.
     *
     * @param period the period string
     * @return the calendar period value
     */
    private int getCalendarPeriodValue(final String period) {
        switch (period) {
            case "day":
                return Calendar.DAY_OF_YEAR;
            case "week":
                return Calendar.WEEK_OF_YEAR;
            case "month":
                return Calendar.MONTH;
            case "year":
                return Calendar.YEAR;
            default:
                return -1;
        }
    }
}
