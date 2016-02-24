package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 * The type Users dao.
 */
public class UsersDAOImpl  implements UsersDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(UsersDAOImpl.class);

    /**
     * The Entity manager factory.
     */
    private EntityManagerFactory entityManagerFactory;

    /**
     * Instantiates a new Users dao.
     */
    public UsersDAOImpl() {
        entityManagerFactory =
                EntityManagerFactorySingleton.getInstance().getFactory();
    }
    @Override
    public final UserEntity addUser(final UserEntity newUser) throws DaoException {
        EntityManager manager = entityManagerFactory.createEntityManager();

        EntityTransaction tr = manager.getTransaction();
        try {

            tr.begin();
            manager.persist(newUser);

            tr.commit();
            return newUser;
        } catch (RollbackException e) {
            tr.rollback();
            LOGGER.error("add user transaction rollbacked", e);
            throw new DaoException("adding user is rollbacked", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public final UserEntity getUserByEmail(final String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();

        try {
            Query query = manager.createNamedQuery("UserEntity.getByEmail");
            query.setParameter("email", email);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public final UserEntity getUserById(final int id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return manager.find(UserEntity.class, id);
        } finally {
            manager.close();
        }
    }

    @Override
    public final UserEntity updateUser(final UserEntity user) throws  DaoException{

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tr = manager.getTransaction();
        try {

            tr.begin();

            manager.merge(user);
            tr.commit();
            return user;
        } catch (RollbackException e) {
            tr.rollback();
            LOGGER.error("update user transaction rollbacked", e);
            throw new DaoException("adding user is rollbacked", e);
        } finally {
            manager.close();
        }
    }
}