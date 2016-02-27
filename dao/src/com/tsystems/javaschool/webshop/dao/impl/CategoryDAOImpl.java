package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Category dao implementation.
 */
public class CategoryDAOImpl  extends AbstractGenericDAO<CategoryEntity>
        implements CategoryDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CategoryDAOImpl.class);
    /**
     * Instantiates a new Category dao.
     */
    public CategoryDAOImpl() {
        super(CategoryEntity.class, LOGGER);
    }
}

