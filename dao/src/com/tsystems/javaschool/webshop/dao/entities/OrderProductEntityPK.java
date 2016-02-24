package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 22.02.2016.
 */
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
    @Column(name = "product_id")
    @Id
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

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductEntityPK that = (OrderProductEntityPK) o;

        if (orderId != that.orderId) return false;
        return productId == that.productId;

    }

    @Override
    public final int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        return result;
    }
}
