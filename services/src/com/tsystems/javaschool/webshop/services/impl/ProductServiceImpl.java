package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import com.tsystems.javaschool.webshop.services.util.ServiceLoadAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Shide on 21.02.2016.
 */
public class ProductServiceImpl implements ProductService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    private ProductDAO productDAO;
    private ServiceHelper serviceHelper;
    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();

        serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    @Override
    public void add(ProductEntity product) {

        serviceHelper.executeInTransaction(manager -> {

            productDAO.create(product, manager);
        });
    }

    @Override
    public void update(ProductEntity product) {
        serviceHelper.executeInTransaction(manager -> {

            productDAO.update(product, manager);
        });
    }

    @Override
    public void delete(ProductEntity product) {
        serviceHelper.executeInTransaction(manager -> {

            productDAO.delete(product, manager);
        });
    }

    @Override
    public ProductEntity get(int productId) {
        return serviceHelper.load(manager -> {

            return productDAO.getById(productId, manager);
        });


    }

    @Override
    public List<ProductEntity> getAll() {
        return serviceHelper.loadInTransaction(manager -> {

            return productDAO.getAll(manager);
        });
    }

    @Override
    public List<ProductEntity> searchProducts(String searchQuery){
        return  serviceHelper.loadInTransaction((ServiceLoadAction<List<ProductEntity>>) manager -> {


            return null;
        });
    }
}
