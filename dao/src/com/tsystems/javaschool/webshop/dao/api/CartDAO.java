package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;

/**
 * DAO class to interact with Cart Entity.
 */
public interface CartDAO extends GenericDAO<CartEntity> {

    /**
     * Return cart object by specified cookie value.
     * @param cookie cookie value
     * @param manager to perform action
     * @return cart object or null if cart not found
     * @throws DaoException if something wrong
     */
     CartEntity getByCookie(String cookie, EntityManager manager)
             throws DaoException;
}
