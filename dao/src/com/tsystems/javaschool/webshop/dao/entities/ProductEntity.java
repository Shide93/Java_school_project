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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
     * Instantiates a new Product entity.
     */
    public ProductEntity() {
        categories = new LinkedHashSet<>();
        features = new LinkedHashSet<>();
        orders = new LinkedHashSet<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(final int price) {
        this.price = price;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    @Basic
    @Column(name = "stock")
    public Integer getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(final Integer stock) {
        this.stock = stock;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets sku.
     *
     * @return the sku
     */
    @Basic
    @Column(name = "sku")
    public String getSku() {
        return sku;
    }

    /**
     * Sets sku.
     *
     * @param sku the sku
     */
    public void setSku(final String sku) {
        this.sku = sku;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public void setCategories(final Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    /**
     * Gets features.
     *
     * @return the features
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<ProductFeatureEntity> getFeatures() {
        return features;
    }

    /**
     * Sets features.
     *
     * @param features the features
     */
    public void setFeatures(final Set<ProductFeatureEntity> features) {
        this.features = features;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<OrderProductEntity> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(final Set<OrderProductEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", features=" + features +
                ", orders=" + orders +
                '}';
    }
}
