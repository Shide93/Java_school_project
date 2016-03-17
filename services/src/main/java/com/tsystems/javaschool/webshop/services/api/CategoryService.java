package com.tsystems.javaschool.webshop.services.api;

import com.tsystems.javaschool.webshop.dao.entities.Category;

import java.util.List;

/**
 * Service provides product manipulation logic.
 */
public interface CategoryService extends GenericService<Category> {

    /**
     * Gets all id names.
     *
     * @return the all id names
     */
    List<Category> getAllIdNames();

}
