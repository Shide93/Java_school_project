package com.tsystems.javaschool.webshop.dao.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Feature entity.
 */
@Entity
@SuppressWarnings("CheckStyle")
@Table(name = "feature", schema = "web_shop")
public class Feature {
    /**
     * The Id.
     */
    @JsonView(Feature.class)
    private int id;
    /**
     * The Name.
     */
    @JsonView(Feature.class)
    private String name;
    /**
     * The Products.
     */
    private Set<ProductFeature> products;

    /**
     * Instantiates a new Feature entity.
     */
    public Feature() {
        products = new LinkedHashSet<>();
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
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "feature")
    @Fetch(FetchMode.JOIN)
    @OrderBy(value = "productId")
    public Set<ProductFeature> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final Set<ProductFeature> products) {
        this.products = products;
    }

}
