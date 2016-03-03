package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

import java.util.List;

/**
 * Created by Shide on 03.03.2016.
 */
public interface StatisticsService {

    /**
     * count of order with NEW status.
     *
     * @return count of new orders
     */
    int newOrders();

    /**
     * total shop sales
     *
     * @return total sales
     */
    long totalSales();

    /**
     * Month shop sales.
     *
     * @return month sales
     */
    long monthSales();

    /**
     * Top of the best selling products.
     *
     * @param count count of products in top
     * @return the list
     */
    List<ProductEntity> topProducts(int count);

    /**
     * Top of the best customers.
     *
     * @param count count of users in top
     * @return the list
     */
    List<UserEntity> topCustomers(int count);
}
