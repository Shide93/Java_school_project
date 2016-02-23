package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;
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
    private int id;
    private String cookie;
    private int count;
    private int summary;
    private Set<CartProductEntity> items;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cookie", nullable = false, unique = true)
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Basic
    @Column(name = "items_count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "summary")
    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<CartProductEntity> getItems() {
        return items;
    }

    public void setItems(Set<CartProductEntity> products) {
        this.items = products;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                ", count=" + count +
                ", summary=" + summary +
                ", items=" + items +
                '}';
    }
}
