package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface ProductService extends GenericService<ProductEntity> {


    /**
     * Searches products that contain string in name or description.
     * @param searchQuery query to search
     * @return list of products that satisfact query
     * @throws ServiceException
     */
    List<ProductEntity> searchProducts(String searchQuery) throws ServiceException;

}
