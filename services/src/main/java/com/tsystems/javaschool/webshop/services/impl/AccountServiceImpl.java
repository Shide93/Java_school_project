package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.impl.UsersDAOImpl;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Account service.
 */
public class AccountServiceImpl implements AccountService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AccountService.class);

    /**
     * The Users dao.
     */
    private final UsersDAO usersDAO;

    /**
     * The Service helper.
     */
    private final ServiceHelper serviceHelper;

    /**
     * Instantiates a new Account service.
     */
    public AccountServiceImpl() {
        this.usersDAO = new UsersDAOImpl();
        this.serviceHelper = new ServiceHelperImpl(LOGGER);
    }

    /**
     * Instantiates a new Account service.
     *
     * @param usersDAO      the users dao
     * @param serviceHelper the service helper
     */
    public AccountServiceImpl(final UsersDAO usersDAO,
                              final ServiceHelper serviceHelper) {
        this.usersDAO = usersDAO;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public final UserEntity signUpUser(final String name,
                                       final String lastName,
                                       final String email,
                                       final String password)
            throws AccountServiceException {
        return serviceHelper.loadInTransaction(manager -> {
            if (usersDAO.getUserByEmail(email, manager) != null) {
                throw new AccountServiceException("email already registered");
            }
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setIsAdmin(false);

            usersDAO.create(newUser, manager);
            return newUser;
        });


    }


    @Override
    public final UserEntity signInUser(final String email,
                                       final String password)
            throws AccountServiceException {
        return serviceHelper.loadInTransaction(manager -> {
            UserEntity user = usersDAO.getUserByEmail(email, manager);

            if (user == null || !user.getPassword().equals(password)) {
                throw new AccountServiceException(
                        "Wrong password or email");
            }
            return user;
        });

    }
    @Override
    public final UserEntity getUser(final int userID) {
        return serviceHelper.load(manager -> {
            return usersDAO.getById(userID, manager);
        });
    }
    @Override
    public final UserEntity saveProfile(final UserEntity user) {
        return serviceHelper.loadInTransaction(manager -> {

                usersDAO.update(user, manager);
                return user;
        });
    }

    @Override
    public final List<UserEntity> getAll() {
        return serviceHelper.loadInTransaction(manager ->
                usersDAO.getAll(manager));
    }

    @Override
    public final void setUserRights(final int userId, final boolean isAdmin) {
         serviceHelper.executeInTransaction(manager ->
                 usersDAO.getById(userId, manager)
                 .setIsAdmin(isAdmin));
    }
}
