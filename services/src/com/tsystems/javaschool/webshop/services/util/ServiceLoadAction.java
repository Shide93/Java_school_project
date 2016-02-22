package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;

/**
 * Created by Shide on 22.02.2016.
 */
@FunctionalInterface
public interface ServiceLoadAction<T> {

    void performAction(EntityManager manager) throws DaoException;
}
