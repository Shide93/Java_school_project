package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Cart dao implementation.
 */
@Repository
public class CartDAOImpl extends AbstractGenericDAO<Cart>
        implements CartDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);


    @Override
    public final void removeFromCart(final CartProduct cartProduct)
            throws DaoException {
        Query q = manager.
                createNamedQuery("CartProductEntity.removeFromCart");
        q.setParameter("cartId", cartProduct.getCartId());
        q.setParameter("productId", cartProduct.getProductId());
        int n = q.executeUpdate();
        if (n > 1) {
            throw new DaoException("More than one row deleted");
        }
    }
}
