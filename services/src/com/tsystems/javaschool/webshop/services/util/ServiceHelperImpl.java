package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 */
public class ServiceHelperImpl implements ServiceHelper {
    private EntityManagerFactory entMgrFactory;

    public ServiceHelperImpl() {
        entMgrFactory = EntityManagerFactorySingleton.getInstance().getFactory();
    }
    //TODO: add exception messages
    @Override
    public void executeTransactionally(ServiceExecuteAction action) throws ServiceException {

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
    public <T> T load(ServiceLoadAction<T> action) throws ServiceException {
        EntityManager manager = null;
        try {
            manager = entMgrFactory.createEntityManager();
            return action.performAction(manager);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public <T> T loadTransactionally(ServiceLoadAction<T> action) throws ServiceException {

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
