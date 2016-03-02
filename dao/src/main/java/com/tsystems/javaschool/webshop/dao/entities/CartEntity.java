package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@NamedQueries({

})
@Table(name = "cart", schema = "web_shop")
public class CartEntity {
    /**
     * The Id in table.
     */
    private int id;
    /**
     * The Count of items in cart.
     */
    private int count;
    /**
     * The Summary.
     */
    private int summary;
    /**
     * The Items in cart.
     */
    private Set<CartProductEntity> items;

    /**
     * Instantiates a new Cart entity.
     */
    public CartEntity() {
        items = new LinkedHashSet<>();
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
     * Gets count.
     *
     * @return the count
     */
    @Basic
    @Column(name = "items_count")
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    @Basic
    @Column(name = "summary")
    public int getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(final int summary) {
        this.summary = summary;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "productId")
    public Set<CartProductEntity> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param products the products
     */
    public void setItems(final Set<CartProductEntity> products) {
        this.items = products;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", count=" + count +
                ", summary=" + summary +
                ", items=" + items +
                '}';
    }
}
