package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "cart_product", schema = "web_shop", catalog = "")
@IdClass(CartProductEntityPK.class)
public class CartProductEntity {
    private int cartId;
    private int productId;
    private Integer quantity;

    @Id
    @Column(name = "cart_id")
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartProductEntity that = (CartProductEntity) o;

        if (cartId != that.cartId) return false;
        if (productId != that.productId) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId;
        result = 31 * result + productId;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
