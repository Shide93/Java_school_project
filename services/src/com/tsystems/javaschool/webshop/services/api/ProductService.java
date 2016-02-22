package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface ProductService {

    /**
     *
     * @param product
     * @throws ServiceException
     */
    void addProduct(ProductEntity product) throws ServiceException;

    /**
     *
     * @param product
     * @throws ServiceException
     */
    void updateProduct(ProductEntity product) throws ServiceException;

    /**
     *
     * @param product
     * @throws ServiceException
     */
    void deleteProduct(ProductEntity product) throws ServiceException;

    /**
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
    ProductEntity getProduct(int productId) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<ProductEntity> getAllProducts() throws ServiceException;

    /**
     *
     * @param searchQuery
     * @return
     * @throws ServiceException
     */
    List<ProductEntity> searchProducts(String searchQuery) throws ServiceException;


}
