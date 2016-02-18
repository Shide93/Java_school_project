package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "feature_value_product", schema = "web_shop", catalog = "")
@IdClass(FeatureValueProductEntityPK.class)
public class FeatureValueProductEntity {
    private int featureValueId;
    private int productId;

    @Id
    @Column(name = "feature_value_id")
    public int getFeatureValueId() {
        return featureValueId;
    }

    public void setFeatureValueId(int featureValueId) {
        this.featureValueId = featureValueId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureValueProductEntity that = (FeatureValueProductEntity) o;

        if (featureValueId != that.featureValueId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = featureValueId;
        result = 31 * result + productId;
        return result;
    }
}
