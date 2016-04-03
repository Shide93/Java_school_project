package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.*;
import com.tsystems.javaschool.webshop.dao.entities.*;
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
    private List<Payment> payments;
    private List<Shipping> shippings;
    private List<Order> orderList;
    private Payment payment;
    private Shipping shipping;

    @Mock
    private PaymentDAO paymentDAO;

    @Mock
    private ShippingDAO shippingDAO;

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private CartDAO cartDAO;

    @Mock
    private ProductDAO productDAO;

    private Order order;
    private Cart cart;
    private int cartId;
    private int productId;
    private int paymentId;
    private int shippingId;
    private int orderId;
    private CartProduct item;
    private Product product;

    @Before
    public void setUp() {

        cartId = 1;
        productId = 2;
        paymentId = 3;
        shippingId = 4;
        orderId = 5;
        payments = new ArrayList<>();
        shippings = new ArrayList<>();
        orderList = new ArrayList<>();

        payment = new Payment();
        payment.setId(paymentId);
        shipping = new Shipping();
        shipping.setCost(100);
        shipping.setId(shippingId);

        order = new Order();
        order.setPayment(payment);
        order.setShipping(shipping);

        cart = new Cart();
        cart.setId(cartId);
        cart.setSummary(100500);

        product = new Product();
        product.setPrice(100);
        product.setStock(10);

        item = new CartProduct();
        item.setProductId(productId);
        item.setCartId(cartId);
        item.setCart(cart);
        item.setQuantity(10);
        item.setProduct(product);


        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl();
        orderService.setCartDAO(cartDAO);
        orderService.setOrderDAO(orderDAO);
        orderService.setPaymentDAO(paymentDAO);
        orderService.setShippingDAO(shippingDAO);
        orderService.setProductDAO(productDAO);
    }

    @Test
    public void getPaymentTypesSuccess() {
        when(paymentDAO.getAll()).thenReturn(payments);
        assertEquals(orderService.getPaymentTypes(), payments);
    }

    @Test
    public void getShippingTypesSuccess() {
        when(shippingDAO.getAll()).thenReturn(shippings);
        assertEquals(orderService.getShippingTypes(), shippings);
    }

    @Test
    public void createOrderSuccess() {
        when(shippingDAO.getById(shippingId)).thenReturn(shipping);
        when(paymentDAO.getById(paymentId)).thenReturn(payment);
        cart.getItems().add(item);


        orderService.createOrder(order, cart);
        verify(orderDAO).create(order);
        verify(cartDAO).delete(cart.getId());
        verify(productDAO).update(product);
        assertEquals(order.getTotal(), cart.getSummary() + shipping.getCost());
        assertEquals(order.getProducts().size(), 1);
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