package com.tsystems.javaschool.webshop.dao.entities;

import com.tsystems.javaschool.webshop.dao.entities.enums.FeatureType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
    private FeatureType type;
    /**
     * The Products.
     */
    private Set<ProductFeatureEntity> products;

    public FeatureEntity() {
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
     * Gets type.
     *
     * @return the type
     */
    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public FeatureType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final FeatureType type) {
        this.type = type;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "feature")
    @Fetch(FetchMode.JOIN)
    public Set<ProductFeatureEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final Set<ProductFeatureEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "FeatureEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
