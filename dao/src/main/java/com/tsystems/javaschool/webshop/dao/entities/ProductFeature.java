package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The type Product feature entity.
 */
@Entity
@SuppressWarnings("CheckStyle")
@NamedQueries({
        @NamedQuery(name = "ProductFeatureEntity.getAllValues",
                query = "select distinct "
                       + "new ProductFeature(pf.value, pf.featureId)"
                       + "from ProductFeature pf")
})
@Table(name = "product_feature", schema = "web_shop")
@IdClass(ProductFeaturePK.class)
public class ProductFeature {
    /**
     * The Product id.
     */
    private int productId;
    /**
     * The Feature id.
     */
    private int featureId;
    /**
     * The Value.
     */
    private String value;
    /**
     * The Product.
     */
    private Product product;
    /**
     * The Feature.
     */
    private Feature feature;

    /**
     * Instantiates a new Product feature entity.
     */
    public ProductFeature() {
    }

    /**
     * Instantiates a new Product feature entity.
     *
     * @param value     the value
     * @param featureId the feature id
     */
    public ProductFeature(final String value, final int featureId) {
        this.value = value;
        this.featureId = featureId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    @Id
    @Column(name = "product_id")
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
    @Id
    @Column(name = "feature_id")
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

    /**
     * Gets value.
     *
     * @return the value
     */
    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    /**
     * Gets feature.
     *
     * @return the feature
     */
    @ManyToOne
    @JoinColumn(name = "feature_id", updatable = false, insertable = false)
    public Feature getFeature() {
        return feature;
    }

    /**
     * Sets feature.
     *
     * @param feature the feature
     */
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }

}
