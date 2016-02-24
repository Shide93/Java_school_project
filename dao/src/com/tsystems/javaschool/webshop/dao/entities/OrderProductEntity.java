package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Shide on 22.02.2016.
 */
@Entity
@Table(name = "order_product", schema = "web_shop", catalog = "")
@IdClass(OrderProductEntityPK.class)
public class OrderProductEntity {
    /**
     * The Order id.
     */
    private int orderId;
    /**
     * The Product id.
     */
    private int productId;
    /**
     * The Quantity.
     */
    private Integer quantity;
    /**
     * The Order.
     */
    private OrderEntity order;
    /**
     * The Product.
     */
    private ProductEntity product;

    /**
     * Gets order id.
     *
     * @return the order id
     */
    @Id
    @Column(name = "order_id")
    public final int getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public final void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    @Id
    @Column(name = "product_id")
    public final int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public final void setProductId(final int productId) {
        this.productId = productId;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    @Basic
    @Column(name = "quantity")
    public final Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public final void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    public final OrderEntity getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public final void setOrder(final OrderEntity order) {
        this.order = order;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    public final ProductEntity getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public final void setProduct(final ProductEntity product) {
        this.product = product;
    }

    @Override
    public final String toString() {
        return "OrderProductEntity{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
