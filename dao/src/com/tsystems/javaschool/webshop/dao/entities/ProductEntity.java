package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "product", schema = "web_shop")
public class ProductEntity {
    private int id;
    private String name;
    private int price;
    private Integer stock;
    private String description;
    private String sku;
    private Set<CategoryEntity> categories;
    private Set<ProductFeatureEntity> features;
    private Set<OrderProductEntity> orders;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock")
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "sku")
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<ProductFeatureEntity> getFeatures() {
        return features;
    }

    public void setFeatures(Set<ProductFeatureEntity> features) {
        this.features = features;
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<OrderProductEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProductEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", categories=" + categories +
                ", features=" + features +
                ", orders=" + orders +
                '}';
    }
}
