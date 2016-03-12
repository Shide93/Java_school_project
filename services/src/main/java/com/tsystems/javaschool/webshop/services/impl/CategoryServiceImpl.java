package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shide on 22.02.2016.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CategoryServiceImpl.class);

    /**
     * The Category dao.
     */
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public final void add(final CategoryEntity category) {

        categoryDAO.create(category);
    }

    @Override
    public final void update(final CategoryEntity category) {

        CategoryEntity persistedCategory =
                categoryDAO.getById(category.getId());
        persistedCategory.setName(category.getName());
        persistedCategory.setDescription(category.getDescription());
        categoryDAO.update(persistedCategory);
    }

    @Override
    public final void delete(final Integer categoryId) {
        categoryDAO.delete(categoryId);
    }

    @Override
    public final CategoryEntity get(final int categoryId) {

        return categoryDAO.getById(categoryId);
    }

    @Override
    public final List<CategoryEntity> getAll() {

        return categoryDAO.getAll();
    }

    @Override
    public final List<CategoryEntity> getAllIdNames() {

        return categoryDAO.getAllIdNames();
    }

}

