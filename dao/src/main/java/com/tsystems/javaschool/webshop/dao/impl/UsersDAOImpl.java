package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * The type Users dao.
 */
@Repository
public class UsersDAOImpl extends AbstractGenericDAO<User>
        implements UsersDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UsersDAOImpl.class);

    @Override
    public final User getUserByEmail(final String email) {
        try {
            Query query = manager.createNamedQuery("UserEntity.getByEmail");
            query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            /*because in SingUp I need check
            that there is no user with this email
            and I expect that there will be no result*/
            return null;
        }
    }

}
