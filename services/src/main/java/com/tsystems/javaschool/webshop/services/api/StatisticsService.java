package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.dto.StatisticsDTO;

import java.util.Date;
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
     * @param dateFrom the from date
     * @return sales for period
     */
    long periodSales(Date dateFrom);

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
     * @param dateFrom         the from date
     * @param topProductsCount the top products count
     * @param topUsersCount    the top users count
     * @return report shop report
     */
    StatisticsDTO getShopReport(Date dateFrom,
                                Integer topProductsCount,
                                Integer topUsersCount);
    /**
     * Generate access token string.
     *
     * @return access token
     */
    String generateAccessToken();
}
