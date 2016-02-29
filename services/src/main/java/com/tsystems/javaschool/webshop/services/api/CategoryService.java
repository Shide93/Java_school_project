package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface CategoryService extends GenericService<CategoryEntity> {

    /**
     * Gets all id names.
     *
     * @return the all id names
     */
    List<CategoryEntity> getAllIdNames();

}
