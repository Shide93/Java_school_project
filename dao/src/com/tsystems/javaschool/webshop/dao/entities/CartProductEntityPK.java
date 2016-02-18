package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 18.02.2016.
 */
public class CartProductEntityPK implements Serializable {
    private int cartId;
    private int productId;

    @Column(name = "cart_id")
    @Id
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

        CartProductEntityPK that = (CartProductEntityPK) o;

        if (cartId != that.cartId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId;
        result = 31 * result + productId;
        return result;
    }
}
