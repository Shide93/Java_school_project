package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private int id;
    private String name;
    private String type;
    private Set<ProductFeatureEntity> products;

    @Id
    @Column(name = "id")
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "feature")
    public Set<ProductFeatureEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductFeatureEntity> products) {
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
