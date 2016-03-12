package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Implementation of genericDAO interface.
 *
 * @param <T> the type parameter
 */
public class AbstractGenericDAO<T> implements GenericDAO<T> {

    /**
     * The Entity manager.
     */
    @SuppressWarnings("CheckStyle")        //need protected field to enable access to manager from DAO's
    @PersistenceContext
    protected EntityManager manager;

    /**
     * The Type.
     */
    private final Class<T> type;

    /**
     * Instantiates a new Abstract generic dao.
     */
    public AbstractGenericDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public final void create(final T newObj) {
        manager.persist(newObj);
    }

    @Override
    public final T getById(final int id) {
        return manager.find(type, id);
    }
    @Override
    public final void update(final T obj) {
        manager.merge(obj);
    }

    @Override
    public final void delete(final Integer objId) {
        T obj = manager.find(type, objId);
        manager.remove(obj);
    }

    @Override
    public final List<T> getAll() {
            String queryString = "select t from "
                    + type.getSimpleName()
                    + " t";
            TypedQuery<T> query = manager.createQuery(queryString, type);
            return query.getResultList();
    }
}
