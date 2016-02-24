package com.tsystems.javaschool.webshop.dao.entities;

import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.dao.entities.enums.PaymentMethod;
import com.tsystems.javaschool.webshop.dao.entities.enums.ShippingMethod;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "ordr", schema = "web_shop")
public class OrderEntity {
    /**
     * The Id.
     */
    private int id;
    /**
     * The User.
     */
    private UserEntity user;
    /**
     * The Address.
     */
    private AddressEntity address;
    /**
     * The Payment method.
     */
    private PaymentMethod paymentMethod;
    /**
     * The Shipping method.
     */
    private ShippingMethod shippingMethod;
    /**
     * The Order status.
     */
    private OrderStatus orderStatus;
    /**
     * The Products.
     */
    private Set<OrderProductEntity> products;

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    public final UserEntity getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public final void setUser(final UserEntity user) {
        this.user = user;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @ManyToOne
    @JoinColumn(name = "address_id")
    public final AddressEntity getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public final void setAddress(final AddressEntity address) {
        this.address = address;
    }

    /**
     * Gets payment method.
     *
     * @return the payment method
     */
    @Basic
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    public final PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets payment method.
     *
     * @param paymentMethod the payment method
     */
    public final void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets shipping method.
     *
     * @return the shipping method
     */
    @Basic
    @Column(name = "shipping_method")
    @Enumerated(EnumType.STRING)
    public final ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    /**
     * Sets shipping method.
     *
     * @param shippingMethod the shipping method
     */
    public final void setShippingMethod(final ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    @Basic
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    public final OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public final void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public final Set<OrderProductEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public final void setProducts(final Set<OrderProductEntity> products) {
        this.products = products;
    }

    @Override
    public final String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", products=" + products +
                '}';
    }
}
