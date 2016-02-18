package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "feature_value", schema = "web_shop", catalog = "")
public class FeatureValueEntity {
    private int id;
    private int featureId;
    private String value;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureValueEntity that = (FeatureValueEntity) o;

        if (id != that.id) return false;
        if (featureId != that.featureId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + featureId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
