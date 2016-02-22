package com.tsystems.javaschool.webshop.services.util;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Shide on 22.02.2016.
 */
public class GenericServiceImpl implements GenericService{
    private EntityManagerFactory entMgrFactory;

    public GenericServiceImpl() {
        entMgrFactory = EntityManagerFactorySingleton.getInstance().getFactory();
    }

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
        return null;
    }

    @Override
    public <T> T loadTransactionally(ServiceLoadAction<T> action) throws ServiceException {
        return null;
    }
}
