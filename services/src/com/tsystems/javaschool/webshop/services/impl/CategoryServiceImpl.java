package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.impl.CategoryDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
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
    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

    private CategoryDAO categoryDAO;
    private ServiceHelper serviceHelper;
    public CategoryServiceImpl() {
        categoryDAO = new CategoryDAOImpl();
        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public void add(CategoryEntity category) {
        serviceHelper.executeInTransaction(manager -> {
            categoryDAO.create(category, manager);
        });
    }

    @Override
    public void update(CategoryEntity category) {
        serviceHelper.executeInTransaction(manager -> {
            categoryDAO.update(category, manager);
        });
    }

    @Override
    public void delete(CategoryEntity category) {
        serviceHelper.executeInTransaction(manager -> {
            categoryDAO.delete(category, manager);
        });
    }

    @Override
    public CategoryEntity get(int categoryId) {
        return serviceHelper.loadInTransaction(manager -> categoryDAO.getById(categoryId, manager));
    }

    @Override
    public List<CategoryEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> categoryDAO.getAll(manager));
    }
}
