package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;


/**
 * DAO class to interact with Cart Entity.
 */
public interface CartDAO extends GenericDAO<CartEntity> {

    /**
     * Removes product from cart.
     *
     * @param productId the product id
     * @param cartId    the cart id
     * @throws DaoException ifDeleted more than one row
     */
    void removeFromCart(final Integer productId,
                        final Integer cartId)
            throws DaoException;
}
