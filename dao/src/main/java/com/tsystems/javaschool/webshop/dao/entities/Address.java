package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of address.
 */
@SuppressWarnings("CheckStyle")
@Entity
@Table(name = "address", schema = "web_shop")
public class Address {
    /**
     * id in table.
     */
    private int id;
    /**
     * address country.
     */
    private String country;
    /**
     * address region.
     */
    private String region;
    /**
     * address city.
     */
    private String city;
    /**
     * address zip.
     */
    private int zip;
    /**
     * address street.
     */
    private String street;
    /**
     * address building.
     */
    private int building;
    /**
     * address flat.
     */
    private int flat;


    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    @Basic
    @Column(name = "zip")
    public int getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(final int zip) {
        this.zip = zip;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(final String street) {
        this.street = street;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    @Basic
    @Column(name = "building")
    public int getBuilding() {
        return building;
    }

    /**
     * Sets building.
     *
     * @param building the building
     */
    public  void setBuilding(final int building) {
        this.building = building;
    }

    /**
     * Gets flat.
     *
     * @return the flat
     */
    @Basic
    @Column(name = "flat")
    public int getFlat() {
        return flat;
    }

    /**
     * Sets flat.
     *
     * @param flat the flat
     */
    public void setFlat(final int flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", flat=" + flat +
                '}';
    }
}

