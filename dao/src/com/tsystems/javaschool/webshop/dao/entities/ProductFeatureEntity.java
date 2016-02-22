package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Shide on 22.02.2016.
 */
@Entity
@Table(name = "product_feature", schema = "web_shop")
@IdClass(ProductFeatureEntityPK.class)
public class ProductFeatureEntity {
    private int productId;
    private int featureId;
    private String value;
    private ProductEntity product;
    private FeatureEntity feature;

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "feature_id")
    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "feature_id", updatable = false, insertable = false)
    public FeatureEntity getFeature() {
        return feature;
    }

    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "ProductFeatureEntity{" +
                "productId=" + productId +
                ", featureId=" + featureId +
                ", value='" + value + '\'' +
                '}';
    }
}
