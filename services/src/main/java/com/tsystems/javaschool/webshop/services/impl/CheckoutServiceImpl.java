package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.*;
import com.tsystems.javaschool.webshop.dao.entities.Address;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.CheckoutService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The type Checkout service.
 */
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CheckoutService.class);

    /**
     * The Payment dao.
     */
    @Autowired
    private PaymentDAO paymentDAO;
    /**
     * The Shipping dao.
     */
    @Autowired
    private ShippingDAO shippingDAO;
    /**
     * The Order dao.
     */
    @Autowired
    private OrderDAO orderDAO;
    /**
     * The Cart dao.
     */
    @Autowired
    private CartDAO cartDAO;


    @Override
    public final List<Payment> getPaymentTypes() {
        return paymentDAO.getAll();
    }

    @Override
    public final List<Shipping> getShippingTypes() {
        return shippingDAO.getAll();
    }

    @Override
    public final void createOrder(final Order order,
                                  final Cart cart) {

        Payment payment = paymentDAO.getById(order.getPayment().getId());
        Shipping shipping = shippingDAO.getById(order.getShipping().getId());
        order.setOrderStatus(OrderStatus.NEW);
        order.setPayment(payment);
        order.setShipping(shipping);
        order.setOrderDate(new Date());
        order.setTotal(cart.getSummary() + shipping.getCost());
        orderDAO.create(order);
        Set<OrderProduct> orderItems = order.getProducts();
        for (CartProduct cartItem : cart.getItems()) {
            OrderProduct orderItem = new OrderProduct();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setOrder(order);
            orderItem.setOrderId(order.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setProducts(orderItems);

        cartDAO.delete(cart.getId());
    }

    /**
     * Sets payment dao.
     *
     * @param dao injecting dao
     */
    public final void setPaymentDAO(final PaymentDAO dao) {
        this.paymentDAO = dao;
    }

    /**
     * Sets shipping dao.
     *
     * @param dao injecting dao
     */
    public final void setShippingDAO(final ShippingDAO dao) {
        this.shippingDAO = dao;
    }

    /**
     * Sets order dao.
     *
     * @param dao injecting dao
     */
    public final void setOrderDAO(final OrderDAO dao) {
        this.orderDAO = dao;
    }

    /**
     * Sets cart dao.
     *
     * @param dao injecting dao
     */
    public final void setCartDAO(final CartDAO dao) {
        this.cartDAO = dao;
    }
}
