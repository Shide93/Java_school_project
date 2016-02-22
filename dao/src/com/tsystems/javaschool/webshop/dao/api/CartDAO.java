package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;

/**
 * Created by Shide on 22.02.2016.
 */
public interface CartDAO extends GenericDAO<CartEntity> {

    /**
     * Return cart object by specified cookie value
     * @param cookie cookie value
     * @param manager to perform action
     * @return cart object or null if cart not found
     * @throws DaoException if smth wrong
     */
    CartEntity getByCookie(String cookie, EntityManager manager) throws DaoException;
}
