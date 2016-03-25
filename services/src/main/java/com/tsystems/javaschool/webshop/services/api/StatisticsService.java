package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.dto.StatisticsDTO;

import java.util.List;

/**
 * The interface Statistics service.
 */
public interface StatisticsService {

    /**
     * count of order with NEW status.
     *
     * @return count of new orders
     */
    int newOrders();

    /**
     * total shop sales.
     *
     * @return total sales
     */
    long totalSales();


    /**
     * Period sales.
     *
     * @param period is a Calendar constant
     * @return sales for period
     */
    long periodSales(int period);

    /**
     * Top of the best selling products.
     *
     * @param count count of products in top
     * @return the list
     */
    List<Product> topProducts(int count);

    /**
     * Top of the best customers.
     *
     * @param count count of users in top
     * @return the list
     */
    List<User> topCustomers(int count);

    /**
     * Gets report object that sends by webservice.
     *
     * @param period           the period of total sales
     * @param topProductsCount the top products count
     * @param topUsersCount    the top users count
     * @param minStock         the min stock
     * @return report shop report
     */
    StatisticsDTO getShopReport(String period,
                                Integer topProductsCount,
                                Integer topUsersCount,
                                Integer minStock);
}
