package com.tsystems.javaschool.webshop.dao.api;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO class to interact with Category Entity.
 */
public interface CategoryDAO extends GenericDAO<CategoryEntity> {

    /**
     * Returns collection of category ids and names.
     *
     * @param manager the manager
     * @return List of categories with only id and names fields
     */
    List<CategoryEntity> getAllIdNames(EntityManager manager);

}
