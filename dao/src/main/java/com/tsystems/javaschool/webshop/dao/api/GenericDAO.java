package com.tsystems.javaschool.webshop.dao.api;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Generic DAO interface. Provides CRUD operations with entities.
 *
 * @param <T> object to work with
 */
public interface GenericDAO<T> {
    /**
     * Creates object in DB.
     *
     * @param newObj  object to save
     * @param manager EntityManager to perform action
     */
    void create(T newObj, EntityManager manager);

    /**
     * Reads an object from DB.
     *
     * @param id      identificator of object in DB
     * @param manager EntityManager to perform action
     * @return found object or null if object not found
     */
    T getById(int id, EntityManager manager);

    /**
     * Updates existing object in DB.
     *
     * @param obj     object to update
     * @param manager EntityManager to perform action
     */
    void update(T obj, EntityManager manager);

    /**
     * Deletes object from DB.
     *
     * @param objId   obj id to delete
     * @param manager EntityManager to perform action
     */
    void delete(Integer objId, EntityManager manager);

    /**
     * Lists all objects of specified type.
     *
     * @param manager EntityManager to perform action
     * @return list of objects of specified type
     */
    List<T> getAll(EntityManager manager);
}
