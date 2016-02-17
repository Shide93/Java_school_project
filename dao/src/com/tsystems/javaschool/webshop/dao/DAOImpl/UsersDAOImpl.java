package com.tsystems.javaschool.webshop.dao.DAOImpl;

import com.tsystems.javaschool.webshop.dao.DAOInterfaces.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

/**
 *
 */
public class UsersDAOImpl  implements UsersDAO {

    private static final Logger LOGGER = LogManager.getLogger(UsersDAOImpl.class);

    private EntityManagerFactory entityManagerFactory;

    public UsersDAOImpl() {
        entityManagerFactory = EntityManagerFactorySingleton.getInstance().getFactory();
    }

    public int addUser(UserEntity newUser) throws DaoException {
        EntityManager manager = entityManagerFactory.createEntityManager();

        EntityTransaction tr = manager.getTransaction();
        try {
            int userId = 0;
            tr.begin();
            manager.persist(newUser);
            userId = newUser.getId();
            tr.commit();
            return userId;
        } catch (RollbackException e) {
            tr.rollback();
            LOGGER.error("add user transaction rollbacked", e);
            throw new DaoException("adding user is rollbacked", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
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

}
