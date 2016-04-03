package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.api.PaymentDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.api.ShippingDAO;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.OrderProduct;
import com.tsystems.javaschool.webshop.dao.entities.Payment;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.Shipping;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.OrderService;
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
public class OrderServiceImpl implements OrderService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderService.class);

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

    /**
     * The Product dao.
     */
    @Autowired
    private ProductDAO productDAO;


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
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setSummary(cartItem.getQuantity()
                    * cartItem.getProduct().getPrice());
            orderItems.add(orderItem);

            Product product = cartItem.getProduct();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productDAO.update(product);
        }
        order.setProducts(orderItems);

        cartDAO.delete(cart.getId());
    }

    @Override
    public final Order get(final int orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public final List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public final void changeStatus(final int id, final OrderStatus status) {
        Order order = orderDAO.getById(id);
        order.setOrderStatus(status);
    }

    @Override
    public final List<Order> getAllByUser(final int userId) {
        return orderDAO.getByUser(userId);
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

    /**
     * Sets product dao.
     *
     * @param dao the dao
     */
    public final void setProductDAO(final ProductDAO dao) {
        this.productDAO = dao;
    }
}
