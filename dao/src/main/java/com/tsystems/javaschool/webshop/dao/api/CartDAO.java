package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;


/**
 * DAO class to interact with Cart Entity.
 */
public interface CartDAO extends GenericDAO<Cart> {

    /**
     * Removes product from cart.
     *
     * @param cartProduct the cart product
     * @throws DaoException ifDeleted more than one row
     */
    void removeFromCart(final CartProduct cartProduct)
            throws DaoException;
}
