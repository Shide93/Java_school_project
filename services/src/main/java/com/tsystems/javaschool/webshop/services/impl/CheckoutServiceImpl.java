package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.OrderProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.PaymentEntity;
import com.tsystems.javaschool.webshop.dao.entities.ShippingEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
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
    public final List<PaymentEntity> getPaymentTypes() {

        return paymentDAO.getAll();
    }

    @Override
    public final List<ShippingEntity> getShippingTypes() {
        return shippingDAO.getAll();
    }

    @Override
    public final void createOrder(final UserEntity user,
                                  final AddressEntity address,
                                  final CartEntity cart,
                                  final Integer paymentId,
                                  final Integer shippingId,
                                  final String comment) {

        PaymentEntity payment = paymentDAO.getById(paymentId);
        ShippingEntity shipping = shippingDAO.getById(shippingId);
        address.setId(0);       //to save new instance of address
        OrderEntity order = new OrderEntity();
        order.setOrderStatus(OrderStatus.NEW);
        order.setPayment(payment);
        order.setShipping(shipping);
        order.setUser(user);
        order.setAddress(address);
        order.setComment(comment);
        order.setOrderDate(new Date());
        order.setTotal(cart.getSummary());
        orderDAO.create(order);
        Set<OrderProductEntity> orderItems = order.getProducts();
        for (CartProductEntity cartItem : cart.getItems()) {
            OrderProductEntity orderItem = new OrderProductEntity();
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
}
