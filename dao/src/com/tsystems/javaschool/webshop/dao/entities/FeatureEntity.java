package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shide on 18.02.2016.
 */
@Entity
@Table(name = "feature", schema = "web_shop", catalog = "")
public class FeatureEntity {
    private int id;
    private String name;
    private String type;
    private String dimension;
    private Set<FeatureValueEntity> featureValues;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "dimension")
    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @OneToMany(mappedBy = "feature")
    public Set<FeatureValueEntity> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(Set<FeatureValueEntity> featureValues) {
        this.featureValues = featureValues;
    }
}
