package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * The type Product feature entity pk.
 */
@SuppressWarnings("CheckStyle")
public class ProductFeaturePK implements Serializable {
    /**
     * The Product id.
     */
    private int productId;
    /**
     * The Feature id.
     */
    private int featureId;

    /**
     * Gets product id.
     *
     * @return the product id
     */
    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(final int productId) {
        this.productId = productId;
    }

    /**
     * Gets feature id.
     *
     * @return the feature id
     */
    @Column(name = "feature_id")
    @Id
    public int getFeatureId() {
        return featureId;
    }

    /**
     * Sets feature id.
     *
     * @param featureId the feature id
     */
    public void setFeatureId(final int featureId) {
        this.featureId = featureId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFeaturePK that = (ProductFeaturePK) o;

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
