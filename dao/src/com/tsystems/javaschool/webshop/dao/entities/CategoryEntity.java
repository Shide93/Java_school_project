package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Category entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CategoryEntity.getAllIdNames",
                query = "select new CategoryEntity(c.id, c.name) from CategoryEntity c")
})
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

    public CategoryEntity() {
        products = new LinkedHashSet<>();
    }
    public CategoryEntity(final int id, final String name) {
        this.id = id;
        this.name = name;
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
     * Gets products.
     *
     * @return the products
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @OrderBy(value = "id")
    public Set<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final Set<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}
