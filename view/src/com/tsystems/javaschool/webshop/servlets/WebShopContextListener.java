package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context listener used to init and destroy applications context.
 */
public class WebShopContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactorySingleton.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactorySingleton.closeFactory();
    }
}
