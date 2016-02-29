package com.tsystems.javaschool.webshop.dao.impl;


import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Product dao implementation.
 */
public class ProductDAOImpl extends AbstractGenericDAO<ProductEntity>
        implements ProductDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductDAOImpl.class);
    /**
     * Instantiates a new Product dao.
     */
    public ProductDAOImpl() {
        super(ProductEntity.class, LOGGER);
    }

}
