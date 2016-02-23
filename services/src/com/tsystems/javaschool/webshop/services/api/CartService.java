package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 *
 */
public interface CartService extends GenericService<CartEntity> {
    /**
     * Search cart by cookie
     * @param cookie cookie of cart needed
     * @return cart or null if not found
     * @throws ServiceException if smth wrong
     */
    CartEntity getByCookie(String cookie) throws ServiceException;
}
