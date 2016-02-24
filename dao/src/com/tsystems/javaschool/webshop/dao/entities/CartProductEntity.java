package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The type Cart product entity.
 */
@Entity
@Table(name = "cart_product", schema = "web_shop", catalog = "")
@IdClass(CartProductEntityPK.class)
public class CartProductEntity {
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
    private CartEntity cart;
    /**
     * The Product.
     */
    private ProductEntity product;

    /**
     * Gets cart id.
     *
     * @return the cart id
     */
    @Id
    @Column(name = "cart_id")
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
     * Gets cart.
     *
     * @return the cart
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    public final CartEntity getCart() {
        return cart;
    }

    /**
     * Sets cart.
     *
     * @param cart the cart
     */
    public final void setCart(final CartEntity cart) {
        this.cart = cart;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
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
        return "CartProductEntity{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
