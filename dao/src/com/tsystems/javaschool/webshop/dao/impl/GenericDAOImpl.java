package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.GenericDAO;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of genericDAO interface.
 */
public class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> type;

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }


    @Override
    public void create(T newObj, EntityManager manager) throws DaoException {
        try {
            manager.persist(newObj);
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public T getById(int id, EntityManager manager) throws DaoException {
        try {
            return manager.find(type, id);
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public void update(T obj, EntityManager manager) throws DaoException {
        try {
            manager.merge(obj);
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public void delete(T obj, EntityManager manager) throws DaoException {
        try {
            manager.remove(obj);
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public List<T> getAll(EntityManager manager) throws DaoException {
        try {
            String queryString = "select t from " +
                    type.getSimpleName() +
                    " t";
            TypedQuery<T> query = manager.createQuery(queryString, type);
            return query.getResultList();
        } catch (Exception e) {
            throw  new DaoException(e);
        }

    }
}
