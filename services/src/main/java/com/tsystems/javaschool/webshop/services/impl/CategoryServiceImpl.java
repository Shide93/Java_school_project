package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.impl.CategoryDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Shide on 22.02.2016.
 */
public class CategoryServiceImpl implements CategoryService {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CategoryServiceImpl.class);

    /**
     * The Category dao.
     */
    private CategoryDAO categoryDAO;
    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;

    /**
     * Instantiates a new Category service.
     */
    public CategoryServiceImpl() {
        categoryDAO = new CategoryDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public final void add(final CategoryEntity category) {
        serviceHelper.executeInTransaction(manager ->
                categoryDAO.create(category, manager));
    }

    @Override
    public final void update(final CategoryEntity category) {
        serviceHelper.executeInTransaction(manager -> {
            CategoryEntity persistedCategory =
                    categoryDAO.getById(category.getId(), manager);
            persistedCategory.setName(category.getName());
            persistedCategory.setDescription(category.getDescription());
            categoryDAO.update(persistedCategory, manager);
        });
    }

    @Override
    public final void delete(final Integer categoryId) {
        serviceHelper.executeInTransaction(manager ->
                categoryDAO.delete(categoryId, manager));
    }

    @Override
    public final CategoryEntity get(final int categoryId) {
        return serviceHelper.loadInTransaction(manager ->
                categoryDAO.getById(categoryId, manager));
    }

    @Override
    public final List<CategoryEntity> getAll() {
        return serviceHelper.loadInTransaction(manager ->
                categoryDAO.getAll(manager));
    }

    @Override
    public final List<CategoryEntity> getAllIdNames() {
        return serviceHelper.loadInTransaction(manager ->
                categoryDAO.getAllIdNames(manager));
    }
}
