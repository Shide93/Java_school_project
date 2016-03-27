package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;

/**
 * The type Order product entity.
 */
@Entity
@SuppressWarnings("CheckStyle")
@NamedQueries({

    @NamedQuery(name = "OrderProductEntity.getTopProducts",
        query = "select p.product from OrderProduct p " +
                "join p.order o where o.orderDate > :dateFrom "
                + "group by p.productId order by sum(p.quantity) desc"),
    @NamedQuery(name = "OrderProductEntity.getProductTotal",
            query = "select sum(op.quantity) from OrderProduct op " +
                    "join op.order o " +
                    "where op.productId=:productId and " +
                    "o.orderDate > :dateFrom ") //TODO: change on orderProduct.price
})
@Table(name = "order_product", schema = "web_shop")
@IdClass(OrderProductPK.class)
public class OrderProduct {
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
    private Order order;
    /**
     * The Product.
     */
    private Product product;

    /**
     * Gets order id.
     *
     * @return the order id
     */
    @Id
    @Column(name = "order_id")
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
     * Gets order.
     *
     * @return the order
     */
    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(final Order order) {
        this.order = order;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
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
}
