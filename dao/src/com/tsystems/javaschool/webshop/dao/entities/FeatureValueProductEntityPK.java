package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 18.02.2016.
 */
public class FeatureValueProductEntityPK implements Serializable {
    private int featureValueId;
    private int productId;

    @Column(name = "feature_value_id")
    @Id
    public int getFeatureValueId() {
        return featureValueId;
    }

    public void setFeatureValueId(int featureValueId) {
        this.featureValueId = featureValueId;
    }

    @Column(name = "product_id")
    @Id
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

        FeatureValueProductEntityPK that = (FeatureValueProductEntityPK) o;

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
