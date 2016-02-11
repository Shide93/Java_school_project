package com.tsystems.javaschool.webshop.dao;

import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;

import javax.persistence.*;

/**
 *
 */
public class UsersDAOImpl  implements UsersDAO{

    private EntityManagerFactory entityManagerFactory;

    public UsersDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("webShopDB");
    }

    public void addUser(User newUser) throws DaoException {
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

    public boolean isRegistered(String email) {

        EntityManager manager = entityManagerFactory.createEntityManager();

        try{
            Query query = manager.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", email);
            if(query.getResultList().size() > 0) {
                return true;
            }
        }finally {
            manager.close();
        }
        return false;
    }
}
