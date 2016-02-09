package com.tsystems.javaschool.webshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 */
public class UsersDAO {
    public void addUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webShopDB");
        EntityManager em = emf.createEntityManager();

        User u = new User();
        u.setName("asd");
        u.setPassword("asdsa");
        u.setEmail("ew@ds.rt");
        u.setUserType("user");
        u.setAddress("user");
        u.setBirthDate("user");
        u.setUserType("user");
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.addUser();
    }
}
