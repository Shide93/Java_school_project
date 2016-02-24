package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * The type Product entity.
 */
@Entity
@Table(name = "product", schema = "web_shop")
public class ProductEntity {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Price.
     */
    private int price;
    /**
     * The Stock.
     */
    private Integer stock;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Sku.
     */
    private String sku;
    /**
     * The Categories.
     */
    private Set<CategoryEntity> categories;
    /**
     * The Features.
     */
    private Set<ProductFeatureEntity> features;
    /**
     * The Orders.
     */
    private Set<OrderProductEntity> orders;

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name")
    public final String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    @Basic
    @Column(name = "price")
    public final int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public final void setPrice(final int price) {
        this.price = price;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    @Basic
    @Column(name = "stock")
    public final Integer getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public final void setStock(final Integer stock) {
        this.stock = stock;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @Basic
    @Column(name = "description")
    public final String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets sku.
     *
     * @return the sku
     */
    @Basic
    @Column(name = "sku")
    public final String getSku() {
        return sku;
    }

    /**
     * Sets sku.
     *
     * @param sku the sku
     */
    public final void setSku(final String sku) {
        this.sku = sku;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    public final Set<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public final void setCategories(final Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    /**
     * Gets features.
     *
     * @return the features
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public final Set<ProductFeatureEntity> getFeatures() {
        return features;
    }

    /**
     * Sets features.
     *
     * @param features the features
     */
    public final void setFeatures(final Set<ProductFeatureEntity> features) {
        this.features = features;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public final Set<OrderProductEntity> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public final void setOrders(final Set<OrderProductEntity> orders) {
        this.orders = orders;
    }

    @Override
    public final String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", categories=" + categories +
                ", features=" + features +
                ", orders=" + orders +
                '}';
    }
}
