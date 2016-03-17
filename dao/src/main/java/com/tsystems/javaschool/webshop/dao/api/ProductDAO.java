package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Product;

import java.util.List;
import java.util.Map;

/**
 * DAO class to interact with Product Entity.
 */
public interface ProductDAO extends GenericDAO<Product> {

    /**
     * Searches products that have specified feature values.
     *
     * @param featureValues map of search values where key
     *                      is feature id and value is
     *                      an array of feature values to search with
     * @return the list of products that satisfy selected features
     */
    List<Product> findByFeatures(
            Map<Integer, List<String>> featureValues);
    /**
     * Top of the best selling products.
     *
     * @param count   count of products in top
     * @return the list
     */
    List<Product> topProducts(int count);
}
