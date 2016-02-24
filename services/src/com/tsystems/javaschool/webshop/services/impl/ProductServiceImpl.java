package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import com.tsystems.javaschool.webshop.services.util.ServiceLoadAction;

import java.util.List;

/**
 * Created by Shide on 21.02.2016.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ServiceHelper serviceHelper;
    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();

        serviceHelper = new ServiceHelperImpl();
    }

    @Override
    public void add(ProductEntity product) throws ServiceException {

        serviceHelper.executeTransactionally(manager -> {

            productDAO.create(product, manager);
        });
    }

    @Override
    public void update(ProductEntity product) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {

            productDAO.update(product, manager);
        });
    }

    @Override
    public void delete(ProductEntity product) throws ServiceException {
        serviceHelper.executeTransactionally(manager -> {

            productDAO.delete(product, manager);
        });
    }

    @Override
    public ProductEntity get(int productId) throws ServiceException {
        return serviceHelper.load(manager -> {

            return productDAO.getById(productId, manager);
        });


    }

    @Override
    public List<ProductEntity> getAll() throws ServiceException {
        return serviceHelper.loadTransactionally(manager -> {

            return productDAO.getAll(manager);
        });
    }

    @Override
    public List<ProductEntity> searchProducts(String searchQuery) throws ServiceException {
        return  serviceHelper.loadTransactionally((ServiceLoadAction<List<ProductEntity>>) manager -> {


            return null;
        });
    }
}
