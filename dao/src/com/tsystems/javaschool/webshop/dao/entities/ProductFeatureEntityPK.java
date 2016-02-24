package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Shide on 22.02.2016.
 */
public class ProductFeatureEntityPK implements Serializable {
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
    public final int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public final void setProductId(final int productId) {
        this.productId = productId;
    }

    /**
     * Gets feature id.
     *
     * @return the feature id
     */
    @Column(name = "feature_id")
    @Id
    public final int getFeatureId() {
        return featureId;
    }

    /**
     * Sets feature id.
     *
     * @param featureId the feature id
     */
    public final void setFeatureId(final int featureId) {
        this.featureId = featureId;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFeatureEntityPK that = (ProductFeatureEntityPK) o;

        if (productId != that.productId) return false;
        return featureId == that.featureId;

    }

    @Override
    public final int hashCode() {
        int result = productId;
        result = 31 * result + featureId;
        return result;
    }
}
