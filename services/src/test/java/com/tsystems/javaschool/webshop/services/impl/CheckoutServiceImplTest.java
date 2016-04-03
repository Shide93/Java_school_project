package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.*;
import com.tsystems.javaschool.webshop.dao.entities.*;
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
public class CheckoutServiceImplTest {

    private CheckoutServiceImpl checkoutService;
    private List<Payment> payments;
    private List<Shipping> shippings;
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
    private Order order;
    private Cart cart;
    private int cartId;
    private int productId;
    private int paymentId;
    private int shippingId;
    private CartProduct item;

    @Before
    public void setUp() {

        cartId = 1;
        productId = 2;
        paymentId = 3;
        shippingId = 4;
        payments = new ArrayList<>();
        shippings = new ArrayList<>();

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

        item = new CartProduct();
        item.setProductId(productId);
        item.setCartId(cartId);
        item.setCart(cart);
        item.setQuantity(10);

        MockitoAnnotations.initMocks(this);
        checkoutService = new CheckoutServiceImpl();
        checkoutService.setCartDAO(cartDAO);
        checkoutService.setOrderDAO(orderDAO);
        checkoutService.setPaymentDAO(paymentDAO);
        checkoutService.setShippingDAO(shippingDAO);
    }

    @Test
    public void getPaymentTypesSuccess() {
        when(paymentDAO.getAll()).thenReturn(payments);
        assertEquals(checkoutService.getPaymentTypes(), payments);
    }

    @Test
    public void getShippingTypesSuccess() {
        when(shippingDAO.getAll()).thenReturn(shippings);
        assertEquals(checkoutService.getShippingTypes(), shippings);
    }

    @Test
    public void createOrderSuccess() {
        when(shippingDAO.getById(shippingId)).thenReturn(shipping);
        when(paymentDAO.getById(paymentId)).thenReturn(payment);
        cart.getItems().add(item);


        checkoutService.createOrder(order, cart);
        verify(orderDAO).create(order);
        verify(cartDAO).delete(cart.getId());
        assertEquals(order.getTotal(), cart.getSummary() + shipping.getCost());
        assertEquals(order.getProducts().size(), 1);
    }
    
}