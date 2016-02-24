package com.tsystems.javaschool.webshop.dao.impl;


import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;

/**
 * Product dao implementation.
 */
public class ProductDAOImpl extends AbstractGenericDAO<ProductEntity>
        implements ProductDAO {

    /**
     * Instantiates a new Product dao.
     */
    public ProductDAOImpl() {
        super(ProductEntity.class);
    }

}
