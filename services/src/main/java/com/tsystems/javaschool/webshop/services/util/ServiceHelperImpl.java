package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * The type Service helper.
 */
public class ServiceHelperImpl implements ServiceHelper {
    /**
     * logger of the performing class.
     */
    private final Logger logger;


    /**
     * The EntityManager factory.
     */
    private EntityManagerFactory entMgrFactory;

    /**
     * Instantiates a new Service helper.
     *
     * @param lgr the logger of the performing class
     */
    public ServiceHelperImpl(final Logger lgr) {
        entMgrFactory =
                EntityManagerFactorySingleton.getInstance().getFactory();
        this.logger = lgr;
    }
    @Override
    public final void executeInTransaction(final ServiceExecuteAction action) {

        EntityManager manager = null;
        EntityTransaction trx = null;
        try {
            manager = entMgrFactory.createEntityManager();
            trx = manager.getTransaction();
            trx.begin();

            action.performAction(manager);

            trx.commit();
        } finally {
            if (trx != null && trx.isActive()) {
                trx.rollback();
                logger.warn("Transaction rollback!");
            }
            if (manager != null) {
                manager.close();
            }
        }

    }
    @Override
    public final <T> T load(final ServiceLoadAction<T> action) {
        EntityManager manager = null;
        try {
            manager = entMgrFactory.createEntityManager();
            return action.performAction(manager);
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public final <T> T loadInTransaction(final ServiceLoadAction<T> action) {

        EntityManager manager = null;
        EntityTransaction trx = null;
        try {
            manager = entMgrFactory.createEntityManager();
            trx = manager.getTransaction();
            trx.begin();

            T result = action.performAction(manager);

            trx.commit();
            return result;
        } finally {
            if (trx != null && trx.isActive()) {
                trx.rollback();
                logger.warn("Transaction rollback!");
            }
            if (manager != null) {
                manager.close();
            }
        }
    }
}
