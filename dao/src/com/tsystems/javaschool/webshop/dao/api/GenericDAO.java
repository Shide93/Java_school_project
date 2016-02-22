package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Generic DAO interface. Provides CRUD operations with entities.
 * @param <T> object to work with
 */
public interface GenericDAO<T> {
    /**
     * Creates object in DB.
     * @param newObj object to save
     * @param manager EntityManager to perform action
     * @throws DaoException when create failed
     */
    void create(T newObj, EntityManager manager) throws DaoException;

    /**
     * Reads an object from DB
     * @param id identificator of object in DB
     * @param manager EntityManager to perform action
     * @return found object or null if object not found
     * @throws DaoException when read failed
     */
    T getById(int id, EntityManager manager) throws DaoException;

    /**
     * Updates existing object in DB.
     * @param obj object to update
     * @param manager EntityManager to perform action
     * @throws DaoException when update failed
     */
    void update(T obj, EntityManager manager) throws DaoException;

    /**
     * Deletes object from DB.
     * @param obj object to delete
     * @param manager EntityManager to perform action
     * @throws DaoException when delete failed
     */
    void delete(T obj, EntityManager manager) throws DaoException;

    /**
     * Lists all objects of specified type.
     * @param manager EntityManager to perform action
     * @return list of objects of specified type
     * @throws DaoException when retrieving failed
     */
    List<T> getAll(EntityManager manager) throws DaoException;
}
