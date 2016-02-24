package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Primary key for caertProductEntity.
 */
public class CartProductEntityPK implements Serializable {
    /**
     * The Cart id.
     */
    private int cartId;
    /**
     * The Product id.
     */
    private int productId;

    /**
     * Gets cart id.
     *
     * @return the cart id
     */
    @Column(name = "cart_id")
    @Id
    public final int getCartId() {
        return cartId;
    }

    /**
     * Sets cart id.
     *
     * @param cartId the cart id
     */
    public final void setCartId(final int cartId) {
        this.cartId = cartId;
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

        CartProductEntityPK that = (CartProductEntityPK) o;

        if (cartId != that.cartId) return false;
        return productId == that.productId;

    }

    @Override
    public final int hashCode() {
        int result = cartId;
        result = 31 * result + productId;
        return result;
    }
}
