package com.tsystems.javaschool.webshop.dao.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Product entity.
 */
@Entity
@SuppressWarnings("CheckStyle")
@NamedQueries({
        @NamedQuery(name = "Product.getOutOfStockProducts",
                query = "select p from Product p " +
                        "where p.stock < :stockLimit")
})
@Table(name = "product", schema = "web_shop")
public class Product {
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
     * The Categories.
     */
    private Category category;
    /**
     * The Features.
     */
    private List<ProductFeature> features;

    /**
     * Instantiates a new Product entity.
     */
    public Product() {
        features = new ArrayList<>();
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
     * Gets categories.
     *
     * @return the categories
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    /**
     * Sets categories.
     *
     * @param category the category
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * Gets features.
     *
     * @return the features
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @OrderBy(value = "featureId")
    public List<ProductFeature> getFeatures() {
        return features;
    }

    /**
     * Sets features.
     *
     * @param features the features
     */
    public void setFeatures(final List<ProductFeature> features) {
        this.features = features;
    }

}
