package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Context listener used to init and destroy applications context.
 */
public class WebShopContextListener implements ServletContextListener {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductPageServlet.class);

    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        sce.getServletContext().setAttribute("USER_COOKIE_MAX_AGE", 2 * 60);
        EntityManagerFactorySingleton.getInstance();

        //TODO: i think it should't be here...
        CategoryService categoryService = new CategoryServiceImpl();
        try {
            List<CategoryEntity> categories = categoryService.getAll();
            sce.getServletContext().setAttribute("categoryList", categories);
        } catch (ServiceException e) {
            LOGGER.error("category fetch failed!", e);
        }

    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {


        EntityManagerFactorySingleton.closeFactory();
    }
}
