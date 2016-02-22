package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "cart", schema = "web_shop", catalog = "")
public class CartEntity {
    private int id;
    private String code;
    private int userId;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", code='" + code + '\'' +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}
