package com.tsystems.javaschool.webshop.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Shide on 11.02.2016.
 */
public class EntityManagerUtil {
    private final static EntityManagerFactory entityManagerFactory= buildFactory();

    public static EntityManagerFactory buildFactory() {
        return Persistence.createEntityManagerFactory("webShopDB");
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void closeFactory() {
        entityManagerFactory.close();
    }
}
