package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 22.02.2016.
 */
public class OrderProductEntityPK implements Serializable {
    private int orderId;
    private int productId;

    @Column(name = "order_id")
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
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
