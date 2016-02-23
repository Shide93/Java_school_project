package com.tsystems.javaschool.webshop.dao.entities;

import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.dao.entities.enums.PaymentMethod;
import com.tsystems.javaschool.webshop.dao.entities.enums.ShippringMethod;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "ordr", schema = "web_shop")
public class OrderEntity {
    private int id;
    private UserEntity user;
    private AddressEntity address;
    private PaymentMethod paymentMethod;
    private ShippringMethod shippingMethod;
    private OrderStatus orderStatus;
    private Set<OrderProductEntity> products;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "address_id")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Basic
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "shipping_method")
    @Enumerated(EnumType.STRING)
    public ShippringMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippringMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @Basic
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public Set<OrderProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
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
