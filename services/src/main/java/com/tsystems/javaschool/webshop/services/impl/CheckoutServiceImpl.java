package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.*;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.dao.impl.CartDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.PaymentDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ShippingDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CheckoutService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Shide on 28.02.2016.
 */
public class CheckoutServiceImpl implements CheckoutService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CheckoutService.class);

    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;
    /**
     * The Payment dao.
     */
    private PaymentDAO paymentDAO;
    /**
     * The Shipping dao.
     */
    private ShippingDAO shippingDAO;
    /**
     * The Order dao.
     */
    private OrderDAO orderDAO;
    /**
     * The Cart dao.
     */
    private CartDAO cartDAO;

    /**
     * Instantiates a new Checkout service.
     */
    public CheckoutServiceImpl() {
        serviceHelper = new ServiceHelperImpl(LOGGER);
        paymentDAO = new PaymentDAOImpl();
        shippingDAO = new ShippingDAOImpl();
        orderDAO = new OrderDAOImpl();
        cartDAO = new CartDAOImpl();
    }

    @Override
    public List<PaymentEntity> getPaymentTypes() {
        return serviceHelper.loadInTransaction(manager -> {
           return paymentDAO.getAll(manager);
        });
    }

    @Override
    public List<ShippingEntity> getShippingTypes() {
        return serviceHelper.loadInTransaction(manager -> {
            return shippingDAO.getAll(manager);
        });
    }

    @Override
    public void createOrder(final UserEntity user,
                            final AddressEntity address,
                            final CartEntity cart,
                            final Integer paymentId,
                            final Integer shippingId,
                            final String comment) {
        serviceHelper.executeInTransaction(manager -> {

            PaymentEntity payment = paymentDAO.getById(paymentId, manager);
            ShippingEntity shipping = shippingDAO.getById(shippingId, manager);
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
            orderDAO.create(order, manager);
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


            cartDAO.delete(cart.getId(), manager);
        });
    }
}
