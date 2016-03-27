package com.tsystems.javaschool.webshop.dao.entities.dto;

import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO object for sending data by REST service.
 */
public class StatisticsDTO {
    /**
     * Instantiates a new Statistics dto.
     */
    public StatisticsDTO() {
        ordersPerStatus = new HashMap<>();
        topUsers = new ArrayList<>();
        topProducts = new ArrayList<>();
    }

    /**
     * Shop total sales.
     */
    private long totalSales;

    /**
     * Sales for a specified period.
     */
    private long periodSales;

    /**
     * Shop total orders.
     */
    private long totalOrders;

    /**
     * Orders count per status for a specified period.
     */
    private Map<OrderStatus, Integer> ordersPerStatus;

    /**
     * The Top users for a specified period.
     */
    private List<UserDTO> topUsers;

    /**
     * The Top products for a specified period.
     */
    private List<ProductDTO> topProducts;

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
     * Gets period sales.
     *
     * @return the period sales
     */
    public long getPeriodSales() {
        return periodSales;
    }

    /**
     * Sets period sales.
     *
     * @param periodSales the period sales
     */
    public void setPeriodSales(final long periodSales) {
        this.periodSales = periodSales;
    }

    /**
     * Gets total orders.
     *
     * @return the total orders
     */
    public long getTotalOrders() {
        return totalOrders;
    }

    /**
     * Sets total orders.
     *
     * @param totalOrders the total orders
     */
    public void setTotalOrders(final long totalOrders) {
        this.totalOrders = totalOrders;
    }

    /**
     * Gets orders per status.
     *
     * @return the orders per status
     */
    public Map<OrderStatus, Integer> getOrdersPerStatus() {
        return ordersPerStatus;
    }

    /**
     * Sets orders per status.
     *
     * @param ordersPerStatus the orders per status
     */
    public void setOrdersPerStatus(final Map<OrderStatus, Integer> ordersPerStatus) {
        this.ordersPerStatus = ordersPerStatus;
    }

    /**
     * Gets top users.
     *
     * @return the top users
     */
    public List<UserDTO> getTopUsers() {
        return topUsers;
    }

    /**
     * Sets top users.
     *
     * @param topUsers the top users
     */
    public void setTopUsers(final List<UserDTO> topUsers) {
        this.topUsers = topUsers;
    }

    /**
     * Gets top products.
     *
     * @return the top products
     */
    public List<ProductDTO> getTopProducts() {
        return topProducts;
    }

    /**
     * Sets top products.
     *
     * @param topProducts the top products
     */
    public void setTopProducts(final List<ProductDTO> topProducts) {
        this.topProducts = topProducts;
    }
}
