package com.tsystems.javaschool.webshop.dao.entities;

import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;

import javax.persistence.*;
import java.util.LinkedHashSet;
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
    private PaymentEntity payment;
    /**
     * The Shipping method.
     */
    private ShippingEntity shipping;
    /**
     * The Order status.
     */
    private OrderStatus orderStatus;

    /**
     * The Comment.
     */
    private String comment;

    /**
     * The Total.
     */
    private int total;
    /**
     * The Products.
     */
    private Set<OrderProductEntity> products;

    /**
     * Instantiates a new Order entity.
     */
    public OrderEntity() {
        products = new LinkedHashSet<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(final UserEntity user) {
        this.user = user;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public AddressEntity getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(final AddressEntity address) {
        this.address = address;
    }

    /**
     * Gets payment method.
     *
     * @return the payment method
     */
    @OneToOne
    @JoinColumn(name = "payment_id")
    public PaymentEntity getPayment() {
        return payment;
    }

    /**
     * Sets payment method.
     *
     * @param payment the payment method
     */
    public void setPayment(final PaymentEntity payment) {
        this.payment = payment;
    }

    /**
     * Gets shipping method.
     *
     * @return the shipping method
     */
    @OneToOne
    @JoinColumn(name = "shipping_id")
    public ShippingEntity getShipping() {
        return shipping;
    }

    /**
     * Sets shipping method.
     *
     * @param shipping the shipping method
     */
    public void setShipping(final ShippingEntity shipping) {
        this.shipping = shipping;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    @Basic
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<OrderProductEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final Set<OrderProductEntity> products) {
        this.products = products;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    @Basic
    @Column(name = "total")
    public int getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(final int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", payment=" + payment +
                ", shipping=" + shipping +
                ", orderStatus=" + orderStatus +
                ", comment='" + comment + '\'' +
                ", products=" + products +
                '}';
    }
}

