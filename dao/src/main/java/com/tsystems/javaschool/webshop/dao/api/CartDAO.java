package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;


/**
 * DAO class to interact with Cart Entity.
 */
public interface CartDAO extends GenericDAO<Cart> {

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
