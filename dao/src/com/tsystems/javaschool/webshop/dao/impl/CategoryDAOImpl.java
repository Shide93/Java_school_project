package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;

/**
 * Category dao implementation.
 */
public class CategoryDAOImpl  extends AbstractGenericDAO<CategoryEntity>
        implements CategoryDAO {

    /**
     * Instantiates a new Category dao.
     */
    public CategoryDAOImpl() {
        super(CategoryEntity.class);
    }
}

