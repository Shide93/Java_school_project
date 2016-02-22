package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.impl.CategoryDAOImpl;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;

import java.util.List;

/**
 * Created by Shide on 22.02.2016.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;
    private ServiceHelper serviceHelper;
    public CategoryServiceImpl() {
        categoryDAO = new CategoryDAOImpl();
        serviceHelper = new ServiceHelperImpl();
    }

    @Override
    public void add(CategoryEntity category) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            categoryDAO.create(category, manager);
        });
    }

    @Override
    public void update(CategoryEntity category) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            categoryDAO.create(category, manager);
        });
    }

    @Override
    public void delete(CategoryEntity category) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {
            categoryDAO.create(category, manager);
        });
    }

    @Override
    public CategoryEntity get(int categoryId) throws ServiceException {
        return serviceHelper.load(manager -> categoryDAO.getById(categoryId, manager));
    }

    @Override
    public List<CategoryEntity> getAll() throws ServiceException {
        return serviceHelper.loadTransactionally(manager -> categoryDAO.getAll(manager));
    }
}
