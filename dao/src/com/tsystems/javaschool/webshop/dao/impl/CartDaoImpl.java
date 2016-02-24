package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


/**
 * Cart dao implementation.
 */
public class CartDAOImpl extends AbstractGenericDAO<CartEntity>
        implements CartDAO {

    /**
     * Instantiates a new Cart dao.
     */
    public CartDAOImpl() {
        super(CartEntity.class);
    }

    @Override
    public final CartEntity getByCookie(final String cookie,
                                        final EntityManager manager)
            throws DaoException {
        try {
            TypedQuery<CartEntity> query = manager.createNamedQuery(
                    "CartEntity.getByCookie", CartEntity.class);
            query.setParameter("cookie", cookie);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
            //TODO: need return null but method
            // creates exception is it good to catch like that?
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }
}
