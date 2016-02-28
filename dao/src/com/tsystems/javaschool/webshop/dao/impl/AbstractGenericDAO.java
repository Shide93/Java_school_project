package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.GenericDAO;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import org.apache.log4j.Logger;

import javax.persistence.EntityExistsException;
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
     * Typed logger from concrete DAO class.
     */
    private Logger typedLogger;

    /**
     * The Type parameter.
     */
    private Class<T> type;

    /**
     * Instantiates a new Abstract generic dao.
     *
     * @param pType  the type
     * @param logger the logger
     */
    public AbstractGenericDAO(final Class<T> pType, final Logger logger) {
        this.type = pType;
        this.typedLogger = logger;
    }


    @Override
    public final void create(final T newObj, final EntityManager manager) {
        manager.persist(newObj);
    }

    @Override
    public final T getById(final int id, final EntityManager manager) {
        return manager.find(type, id);
    }
    @Override
    public final void update(final T obj, final EntityManager manager) {
        manager.merge(obj);
    }

    @Override
    public final void delete(final Integer objId, final EntityManager manager) {
        T obj = manager.find(type, objId);
        manager.remove(obj);
    }

    @Override
    public final List<T> getAll(final EntityManager manager) {
            String queryString = "select t from "
                    + type.getSimpleName()
                    + " t";
            TypedQuery<T> query = manager.createQuery(queryString, type);
            return query.getResultList();
    }
}
