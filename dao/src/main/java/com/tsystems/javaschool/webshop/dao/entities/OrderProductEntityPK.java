package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * The type Order product entity pk.
 */
@SuppressWarnings("CheckStyle")
public class OrderProductEntityPK implements Serializable {
    /**
     * The Order id.
     */
    private int orderId;
    /**
     * The Product id.
     */
    private int productId;

    /**
     * Gets order id.
     *
     * @return the order id
     */
    @Column(name = "order_id")
    @Id
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(final int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductEntityPK that = (OrderProductEntityPK) o;

        if (orderId != that.orderId) return false;
        return productId == that.productId;

    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        return result;
    }
}
