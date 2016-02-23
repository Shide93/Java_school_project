package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by Shide on 22.02.2016.
 */
public class CartDAOImpl extends AbstractGenericDAO<CartEntity> implements CartDAO {

    public CartDAOImpl() {
        super(CartEntity.class);
    }

    @Override
    public CartEntity getByCookie(String cookie, EntityManager manager) throws DaoException {
        try {
            TypedQuery<CartEntity> query = manager.createNamedQuery("CartEntity.getByCookie", CartEntity.class);
            query.setParameter("cookie", cookie);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
            //TODO: need return null but method creates exception is it good to catch like that?
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }
}
