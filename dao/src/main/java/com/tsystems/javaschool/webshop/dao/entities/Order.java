package com.tsystems.javaschool.webshop.dao.entities;

import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Order entity.
 */
@Entity
@SuppressWarnings("CheckStyle")
@NamedQueries({
        @NamedQuery(name = "OrderEntity.getByUser",
                query = "select o from Order o where user.id = :id"),
        @NamedQuery(name = "OrderEntity.getWithStatus",
                query = "select o from Order o "
                        + "where o.orderStatus = :orderStatus"),
        @NamedQuery(name = "OrderEntity.totalSales",
                query = "select sum(o.total) from Order o"),
        @NamedQuery(name = "OrderEntity.periodSales",
                query = "select sum(o.total) from Order o"
                        + " where o.orderDate > :date"),
        @NamedQuery(name = "OrderEntity.getTopCustomers",
                query = "select o.user from Order o "
                       + "group by o.user order by sum(o.total) desc")
})
@Table(name = "ordr", schema = "web_shop")
public class Order {
    /**
     * The Id.
     */
    private int id;

    /**
     * The User.
     */
    private User user;

    /**
     * The Address.
     */
    private Address address;

    /**
     * The Payment.
     */
    private Payment payment;

    /**
     * The Shipping.
     */
    private Shipping shipping;

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
    private Set<OrderProduct> products;
    /**
     * The Order date.
     */
    private Date orderDate;

    /**
     * Instantiates a new Order entity.
     */
    public Order() {
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
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Gets payment method.
     *
     * @return the payment method
     */
    @OneToOne
    @JoinColumn(name = "payment_id")
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets payment method.
     *
     * @param payment the payment method
     */
    public void setPayment(final Payment payment) {
        this.payment = payment;
    }

    /**
     * Gets shipping method.
     *
     * @return the shipping method
     */
    @OneToOne
    @JoinColumn(name = "shipping_id")
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * Sets shipping method.
     *
     * @param shipping the shipping method
     */
    public void setShipping(final Shipping shipping) {
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
    @OrderBy(value = "productId")
    public Set<OrderProduct> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final Set<OrderProduct> products) {
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

    /**
     * Gets order date.
     *
     * @return the order date
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(final Date orderDate) {
        this.orderDate = orderDate;
    }

}

