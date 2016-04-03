package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.dto.StatisticsDTO;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 02.04.2016.
 */
public class StatisticsServiceImplTest {
    private StatisticsServiceImpl statisticsService;
    private Date date;
    @Mock
    private OrderDAO orderDAO;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private UsersDAO usersDAO;
    private int topCount;
    private List<User> topUsers;
    private List<Product> topProducts;

    @Before
    public void setUp() {

        date = new Date(0);
        topCount = 5;

        topUsers = new ArrayList<>();
        topProducts = new ArrayList<>();
        for (int i = 0; i < topCount; i++) {
            topUsers.add(new User());
            topProducts.add(new Product());
        }

        MockitoAnnotations.initMocks(this);
        statisticsService = new StatisticsServiceImpl();
        statisticsService.setProductDAO(productDAO);
        statisticsService.setOrderDAO(orderDAO);
        statisticsService.setUsersDAO(usersDAO);
    }
    
    @Test
    public void newOrdersSuccess() {
        statisticsService.newOrders();
        verify(orderDAO).getOrderCountByStatus(OrderStatus.NEW, new Date(0));
    }

    @Test
    public void totalSalesSuccess() {
        statisticsService.totalSales();
        verify(orderDAO).totalSales();
    }

    @Test
    public void periodSalesSuccess() {
        statisticsService.periodSales(date);
        verify(orderDAO).periodSales(date);
    }

    @Test
    public void topProductsSuccess() {
        statisticsService.topProducts(topCount);
        verify(productDAO).topProducts(topCount, date);

    }

    @Test
    public void topCustomersSuccess() {
        statisticsService.topCustomers(topCount);
        verify(orderDAO).topCustomers(topCount, date);
    }

    @Test
    public void getShopReportSuccess() {
        long totalSales = 1000;
        long periodSales = 100;
        long totalOrders = 10;
        when(orderDAO.totalSales()).thenReturn(totalSales);
        when(orderDAO.periodSales(date)).thenReturn(periodSales);
        when(orderDAO.totalOrders()).thenReturn(totalOrders);
        when(orderDAO.topCustomers(topCount, date)).thenReturn(topUsers);
        when(productDAO.topProducts(topCount, date)).thenReturn(topProducts);

        StatisticsDTO report =
                statisticsService.getShopReport(date,
                        topCount, topCount);
        assertEquals(report.getTotalSales(), totalSales);
        assertEquals(report.getPeriodSales(), periodSales);
        assertEquals(report.getTotalOrders(), totalOrders);
        assertEquals(report.getOrdersPerStatus().size(),
                OrderStatus.values().length);
        assertEquals(report.getTopProducts().size(), topCount);
        assertEquals(report.getTopUsers().size(), topCount);


    }
}