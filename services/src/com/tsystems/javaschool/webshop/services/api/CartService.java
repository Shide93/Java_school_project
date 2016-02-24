package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

/**
 *
 */
public interface CartService extends GenericService<CartEntity> {
    /**
     * Search cart by cookie.
     * @param cookie cookie of cart needed
     * @return cart or null if not found
     * @throws ServiceException if smth wrong
     */
    CartEntity getByCookie(String cookie) throws ServiceException;

    /**
     * Method adds product with it's quantity to cart.
     * @param productId id of product to add
     * @param quantity quantity of product in cart
     * @param cart cart to add product
     * @throws ServiceException if smth wrong
     */
    void addToCart(Integer productId,
                   Integer quantity, CartEntity cart)
            throws ServiceException;
}
