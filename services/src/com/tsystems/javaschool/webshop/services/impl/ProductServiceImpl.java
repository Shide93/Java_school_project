package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.GenericService;
import com.tsystems.javaschool.webshop.services.util.GenericServiceImpl;

import java.util.List;

/**
 * Created by Shide on 21.02.2016.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private GenericService genericService;
    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();

        genericService = new GenericServiceImpl();
    }

    @Override
    public void addProduct(ProductEntity product) throws ServiceException {

        genericService.executeTransactionally(manager -> {
            ProductEntity prod = new ProductEntity();
            prod.setName("qwe");
            prod.setPrice(123);
            //TODO: HOW THIS WORKS? WHY Product DAO accessed here???
            productDAO.create(prod, manager);

        });
    }

    @Override
    public void updateProduct(ProductEntity product) throws ServiceException {

    }

    @Override
    public void deleteProduct(ProductEntity product) throws ServiceException {

    }

    @Override
    public ProductEntity getProduct(int productId) throws ServiceException {


        return null;
    }

    @Override
    public List<ProductEntity> getAllProducts() throws ServiceException {
        return null;
    }

    @Override
    public List<ProductEntity> searchProducts(String searchQuery) throws ServiceException {
        return null;
    }

    public static void main(String[] args) throws ServiceException{
        ProductService ps = new ProductServiceImpl();
        ps.addProduct(new ProductEntity());
    }
}
