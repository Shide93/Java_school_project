package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Cart dao implementation.
 */
public class CartDAOImpl extends AbstractGenericDAO<CartEntity>
        implements CartDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartDAOImpl.class);

    /**
     * Instantiates a new Cart dao.
     */
    public CartDAOImpl() {
        super(CartEntity.class, LOGGER);
    }


    @Override
    public final void removeFromCart(final Integer productId,
                               final Integer cartId,
                               final EntityManager manager)
            throws DaoException {
        Query q = manager.
                createNamedQuery("CartProductEntity.removeFromCart");
        q.setParameter("cartId", cartId);
        q.setParameter("productId", productId);
        int n = q.executeUpdate();
        if (n > 1) {
            throw new DaoException("More than one row deleted");
        }
    }
}
