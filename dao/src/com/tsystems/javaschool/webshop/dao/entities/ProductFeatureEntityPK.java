package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 22.02.2016.
 */
public class ProductFeatureEntityPK implements Serializable {
    private int productId;
    private int featureId;

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "feature_id")
    @Id
    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFeatureEntityPK that = (ProductFeatureEntityPK) o;

        if (productId != that.productId) return false;
        return featureId == that.featureId;

    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + featureId;
        return result;
    }
}
