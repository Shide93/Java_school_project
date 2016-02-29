package com.tsystems.javaschool.webshop.dao.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A singleton wrapper about EntityManagerFactory.
 */
public final class EntityManagerFactorySingleton {
    /** Singleton instance.*/
    private static EntityManagerFactorySingleton instance = null;

    /***/
    private EntityManagerFactory factory;

    /***/
    private EntityManagerFactorySingleton() {
        factory = Persistence.createEntityManagerFactory("webShopDB");
    }

    /**
     *
     * @return instance of a singleton
     */
    public static EntityManagerFactorySingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactorySingleton();
        }
        return instance;
    }

    /**
     * Method is used to close factory on application close.
     */
    public static void closeFactory() {
        if (instance != null) {
            instance.factory.close();
        }
    }
    /**
     *
     * @return factory to provide EntityManagers
     */
    public EntityManagerFactory getFactory() {
        return factory;
    }
}
