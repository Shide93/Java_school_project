package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private int id;
    private String cookie;
    private Set<CartProductEntity> products;

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

    @OneToMany(mappedBy = "cart")
    public Set<CartProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<CartProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                ", products=" + products +
                '}';
    }
}
