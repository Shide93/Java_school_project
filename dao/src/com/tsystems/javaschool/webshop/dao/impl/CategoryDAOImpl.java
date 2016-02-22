package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;

/**
 * Created by Shide on 22.02.2016.
 */
public class CategoryDAOImpl  extends AbstractGenericDAO<CategoryEntity> implements CategoryDAO {

    public CategoryDAOImpl() {
        super(CategoryEntity.class);
    }
}

