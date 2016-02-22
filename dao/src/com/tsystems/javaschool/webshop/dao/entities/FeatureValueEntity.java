package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "feature_value", schema = "web_shop", catalog = "")
public class FeatureValueEntity {
    private int id;
    private FeatureEntity feature;
    private String value;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "feature_id")
    public FeatureEntity getFeature() {
        return feature;
    }

    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
