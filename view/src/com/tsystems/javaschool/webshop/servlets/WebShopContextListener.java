package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.impl.CategoryServiceImpl;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

        EntityManagerFactorySingleton.getInstance();
        //Setting categories to context for showing them in sidebar.
        ServletUtils.setCategoryListToContext(sce.getServletContext());
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {

        EntityManagerFactorySingleton.closeFactory();
    }
}
