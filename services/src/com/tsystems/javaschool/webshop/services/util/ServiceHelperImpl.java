package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * The type Service helper.
 */
public class ServiceHelperImpl implements ServiceHelper {
    /**
     * The EntityManager factory.
     */
    private EntityManagerFactory entMgrFactory;

    /**
     * Instantiates a new Service helper.
     */
    public ServiceHelperImpl() {
        entMgrFactory =
                EntityManagerFactorySingleton.getInstance().getFactory();
    }
    //TODO: add exception messages
    @Override
    public final void executeTransactionally(final ServiceExecuteAction action)
            throws ServiceException {

        EntityManager manager = null;
        EntityTransaction trx = null;
        try {
            manager = entMgrFactory.createEntityManager();
            trx = manager.getTransaction();
            trx.begin();

            action.performAction(manager);

            trx.commit();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException();
        } finally {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            if (manager != null) {
                manager.close();
            }
        }

    }

    @Override
    public final <T> T load(final ServiceLoadAction<T> action)
            throws ServiceException {
        EntityManager manager = null;
        try {
            manager = entMgrFactory.createEntityManager();
            return action.performAction(manager);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public final <T> T loadTransactionally(final ServiceLoadAction<T> action)
            throws ServiceException {

        EntityManager manager = null;
        EntityTransaction trx = null;
        try {
            manager = entMgrFactory.createEntityManager();
            trx = manager.getTransaction();
            trx.begin();

            T result = action.performAction(manager);

            trx.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException();
        } finally {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            if (manager != null) {
                manager.close();
            }
        }
    }
}
