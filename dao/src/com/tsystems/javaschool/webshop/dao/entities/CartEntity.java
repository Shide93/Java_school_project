package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CartEntity.getByCookie", query = "SELECT c FROM CartEntity c WHERE cookie = :cookie")
})
@Table(name = "cart", schema = "web_shop")
public class CartEntity {
    /**
     * The Id in table.
     */
    private int id;
    /**
     * The cart Cookie.
     */
    private String cookie;
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
     * Gets cookie.
     *
     * @return the cookie
     */
    @Basic
    @Column(name = "cookie", nullable = false, unique = true)
    public final String getCookie() {
        return cookie;
    }

    /**
     * Sets cookie.
     *
     * @param cookie the cookie
     */
    public final void setCookie(final String cookie) {
        this.cookie = cookie;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    @Basic
    @Column(name = "items_count")
    public final int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public final void setCount(final int count) {
        this.count = count;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    @Basic
    @Column(name = "summary")
    public final int getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public final void setSummary(final int summary) {
        this.summary = summary;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public final Set<CartProductEntity> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param products the products
     */
    public final void setItems(final Set<CartProductEntity> products) {
        this.items = products;
    }

    @Override
    public final String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                ", count=" + count +
                ", summary=" + summary +
                ", items=" + items +
                '}';
    }
}
