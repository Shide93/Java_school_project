package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
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
     * The Category dao.
     */
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public final void add(final Category category) {
        categoryDAO.create(category);
    }

    @Override
    public final void update(final Category category) {
        Category persistedCategory =
                categoryDAO.getById(category.getId());
        persistedCategory.setName(category.getName());
        persistedCategory.setDescription(category.getDescription());
        categoryDAO.update(persistedCategory);
    }

    @Override
    public final void delete(final Integer categoryId)
            throws ServiceException {
        if (categoryDAO.getById(categoryId).getProducts().size() > 0) {
            throw new ServiceException("Can't remove product");
        }
        categoryDAO.delete(categoryId);
    }

    @Override
    public final Category get(final int categoryId) {
        return categoryDAO.getById(categoryId);
    }

    @Override
    public final List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public final List<Category> getAllIdNames() {
        return categoryDAO.getAllIdNames();
    }


    /**
     * Sets category dao.
     *
     * @param categoryDAO the category dao
     */
    public final void setCategoryDAO(final CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}

