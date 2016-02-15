package com.tsystems.javaschool.webshop.dao.DAOImpl;

import com.tsystems.javaschool.webshop.dao.DAOInterfaces.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;

import javax.persistence.*;

/**
 *
 */
public class UsersDAOImpl  implements UsersDAO {

    private EntityManagerFactory entityManagerFactory;

    public UsersDAOImpl() {
        entityManagerFactory = EntityManagerFactorySingleton.getInstance().getFactory();
    }

    public void addUser(UserEntity newUser) throws DaoException {
        EntityManager manager = entityManagerFactory.createEntityManager();

        EntityTransaction tr = manager.getTransaction();
        try {
            tr.begin();
            manager.persist(newUser);
            tr.commit();
        } finally {
            if (tr.isActive()) {
                tr.rollback();
                //log
                throw new DaoException("adding user is rollbacked");

            }
        }
        manager.close();
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
