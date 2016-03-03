package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * DAO class to interact with Product Entity.
 */
public interface ProductDAO extends GenericDAO<ProductEntity> {

    /**
     * Searches products that have specified feature values
     *
     * @param featureValues map of search values where key                      is feature id and value is                      an array of feature values to search with
     * @param manager       the manager
     * @return the list of products that satisfy selected features
     */
    List<ProductEntity> findByFeatures(Map<Integer, List<String>> featureValues, EntityManager manager);


    /**
     * Top of the best selling products.
     *
     * @param count   count of products in top
     * @param manager the manager
     * @return the list
     */
    List<ProductEntity> topProducts(int count, EntityManager manager);
}
