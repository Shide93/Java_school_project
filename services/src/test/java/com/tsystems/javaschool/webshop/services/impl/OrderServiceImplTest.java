package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 02.04.2016.
 */
public class OrderServiceImplTest {

    private OrderServiceImpl orderService;
    private int orderId;
    private Order order;
    private List<Order> orderList;

    @Mock
    private OrderDAO orderDAO;

    @Before
    public void setUp() {
        orderId = 1;
        order = new Order();
        orderList = new ArrayList<>();

        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl();
        orderService.setOrderDAO(orderDAO);
    }

    @Test
    public void addSuccess() {
        orderService.add(order);
        verify(orderDAO).create(order);
    }

    @Test
    public void updateSuccess() {
        orderService.update(order);
        verify(orderDAO).update(order);
    }

    @Test
    public void deleteSuccess() {
        orderService.delete(orderId);
        verify(orderDAO).delete(orderId);
    }

    @Test
    public void getSuccess() {
        order.setId(orderId);
        when(orderDAO.getById(orderId)).thenReturn(order);
        assertEquals(order.getId(),
                orderService.get(orderId).getId());
    }

    @Test
    public void getAllSuccess() {
        orderList.add(order);
        when(orderDAO.getAll()).thenReturn(orderList);
        assertEquals(orderList,
                orderService.getAll());
    }
    @Test
    public void changeStatusSuccess() {
        order.setOrderStatus(OrderStatus.NEW);
        when(orderDAO.getById(orderId)).thenReturn(order);
        orderService.changeStatus(orderId, OrderStatus.PAID);
        assertEquals(order.getOrderStatus(), OrderStatus.PAID);
    }

    @Test
    public void testGetAllByUser() {
        int userId = 1;
        orderList.add(order);
        when(orderDAO.getByUser(userId)).thenReturn(orderList);
        assertEquals(orderList,
                orderService.getAllByUser(userId));
    }
}