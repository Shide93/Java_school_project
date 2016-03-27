package com.tsystems.javaschool.webshop.dao.entities.dto;

import com.tsystems.javaschool.webshop.dao.entities.Product;

/**
 * The type Product dto.
 */
public class ProductDTO {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Price.
     */
    private long price;
    /**
     * The Total sales.
     */
    private long totalSales;
    /**
     * The Stock.
     */
    private long stock;

    /**
     * Instantiates a new Product dto.
     *
     * Fills all fields from {@link Product} object,
     * except totalSales field.
     * totalSales should be filled by setter method
     *
     * @param product the product
     */
    public ProductDTO(final Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public long getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(final long price) {
        this.price = price;
    }

    /**
     * Gets total sales.
     *
     * @return the total sales
     */
    public long getTotalSales() {
        return totalSales;
    }

    /**
     * Sets total sales.
     *
     * @param totalSales the total sales
     */
    public void setTotalSales(final long totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public long getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(final long stock) {
        this.stock = stock;
    }
}
