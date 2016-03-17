package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Category dao implementation.
 */
@Repository
public class CategoryDAOImpl  extends AbstractGenericDAO<Category>
        implements CategoryDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CategoryDAOImpl.class);

    @Override
    public final List<Category> getAllIdNames() {
        TypedQuery<Category> q =
                manager.createNamedQuery("CategoryEntity.getAllIdNames",
                Category.class);
        return q.getResultList();
    }
}

