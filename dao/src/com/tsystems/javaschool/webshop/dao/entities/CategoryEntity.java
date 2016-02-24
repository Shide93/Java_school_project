package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * The type Category entity.
 */
@Entity
@Table(name = "category", schema = "web_shop")
public class CategoryEntity {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Products.
     */
    private Set<ProductEntity> products;

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
     * Gets products.
     *
     * @return the products
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    public final Set<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public final void setProducts(final Set<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public final String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +

                '}';
    }
}
