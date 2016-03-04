package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context listener used to init and destroy applications context.
 */
public class WebShopContextListener implements ServletContextListener {
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
