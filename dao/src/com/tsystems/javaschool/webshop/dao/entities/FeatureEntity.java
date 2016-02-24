package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Shide on 22.02.2016.
 */
@Entity
@Table(name = "feature", schema = "web_shop")
public class FeatureEntity {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Type.
     */
    private String type;
    /**
     * The Products.
     */
    private Set<ProductFeatureEntity> products;

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
     * Gets type.
     *
     * @return the type
     */
    @Basic
    @Column(name = "type")
    public final String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "feature", fetch = FetchType.EAGER)
    public final Set<ProductFeatureEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public final void setProducts(final Set<ProductFeatureEntity> products) {
        this.products = products;
    }

    @Override
    public final String toString() {
        return "FeatureEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
