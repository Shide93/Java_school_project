package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.GenericDAO;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of genericDAO interface.
 *
 * @param <T> the type parameter
 */
public class AbstractGenericDAO<T> implements GenericDAO<T> {

    /**
     * The Type parameter.
     */
    private Class<T> type;

    /**
     * Instantiates a new Abstract generic dao.
     *
     * @param pType the type
     */
    public AbstractGenericDAO(final Class<T> pType) {
        this.type = pType;
    }


    @Override
    public final void create(final T newObj, final EntityManager manager)
            throws DaoException {
        try {
            manager.persist(newObj);
        } catch (final Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public final T getById(final int id, final EntityManager manager)
            throws DaoException {
        try {
            return manager.find(type, id);
        } catch (final Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public final void update(final T obj, final EntityManager manager)
            throws DaoException {
        try {
            manager.merge(obj);
        } catch (final Exception e) {
            //TODO: try-catch somehow
            //TODO: how to revert changes of entity
            throw  new DaoException(e);
        }
    }

    @Override
    public final void delete(final T obj, final EntityManager manager)
            throws DaoException {
        try {
            manager.remove(obj);
        } catch (final Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public final List<T> getAll(final EntityManager manager)
            throws DaoException {
        try {
            String queryString = "select t from "
                   + type.getSimpleName()
                   + " t";
            TypedQuery<T> query = manager.createQuery(queryString, type);
            return query.getResultList();
        } catch (final Exception e) {
            throw  new DaoException(e);
        }

    }
}
