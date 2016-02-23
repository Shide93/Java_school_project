package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;


@Entity
@Table(name = "cart_product", schema = "web_shop", catalog = "")
@IdClass(CartProductEntityPK.class)
public class CartProductEntity {
    private int cartId;
    private int productId;
    private Integer quantity;
    private CartEntity cart;
    private ProductEntity product;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartProductEntity{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
