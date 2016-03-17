package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The type Cart product entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CartProductEntity.removeFromCart",
                query = "delete from CartProduct cpe "
                        + "where cpe.cartId = :cartId "
                        + "and cpe.productId = :productId")
})
@Table(name = "cart_product", schema = "web_shop")
@IdClass(CartProductPK.class)
public class CartProduct {
    /**
     * The Cart id.
     */
    private int cartId;
    /**
     * The Product id.
     */
    private int productId;
    /**
     * The Quantity.
     */
    private Integer quantity;
    /**
     * The Cart.
     */
    private Cart cart;
    /**
     * The Product.
     */
    private Product product;

    /**
     * Gets cart id.
     *
     * @return the cart id
     */
    @Id
    @Column(name = "cart_id")
    public int getCartId() {
        return cartId;
    }

    /**
     * Sets cart id.
     *
     * @param cartId the cart id
     */
    public void setCartId(final int cartId) {
        this.cartId = cartId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    @Id
    @Column(name = "product_id")
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

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets cart.
     *
     * @return the cart
     */
    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets cart.
     *
     * @param cart the cart
     */
    public void setCart(final Cart cart) {
        this.cart = cart;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CartProduct that = (CartProduct) o;

        return cartId == that.cartId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        int result = cartId;
        result = 31 * result + productId;
        return result;
    }
}
