package com.tsystems.javaschool.webshop.services;

import com.tsystems.javaschool.webshop.dao.DAOInterfaces.UsersDAO;
import com.tsystems.javaschool.webshop.dao.DAOImpl.UsersDAOImpl;
import com.tsystems.javaschool.webshop.dao.entities.AddressEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.dao.exceptions.DaoException;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 *
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    private UsersDAO usersDAO;

    public AccountServiceImpl() {
        this.usersDAO = new UsersDAOImpl();
    }


    public UserEntity signUpUser(String name, String lastName, String email, String password) throws ServiceException {

        //TODO: validate email, hash password

        if (usersDAO.getUserByEmail(email) != null) {
            throw new ServiceException("email registered");
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setIsAdmin(false);
        try {
            return usersDAO.addUser(newUser);
        } catch (DaoException e) {
            LOGGER.error("Adding user failed", e);
            throw new ServiceException("Adding user failed", e);
        }

    }


    @Override
    public UserEntity signInUser(String email, String password) throws ServiceException{

        UserEntity user = usersDAO.getUserByEmail(email);

        if (user == null) {
            throw new ServiceException("User with this email not found");
        }
        //TODO: hash password

        if (!user.getPassword().equals(password)) {
            throw new ServiceException("Wrong password");
        }
        return user;

    }

    @Override
    public UserEntity getUser(int userID) {
        return usersDAO.getUserById(userID);
    }

    @Override
    public UserEntity saveProfile(String email,
                           String password,
                           String name,
                           String lastName,
                           String phone,
                           Date birthDate,
                           String country,
                           String region,
                           String city,
                           Integer zip,
                           String addr,
                           UserEntity oldUser)
            throws ServiceException {


        UserEntity user = new UserEntity();
        user.setId(oldUser.getId());
        user.setName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsAdmin(false);
        AddressEntity address = new AddressEntity();
        if (oldUser.getAddress() != null) {
            address.setId(oldUser.getAddress().getId());
        }
        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        if (zip != null) {
            address.setZip(zip);
        }
        address.setAddr(addr);
        user.setAddress(address);

        try {
            return usersDAO.updateUser(user);
        } catch (DaoException e) {
            LOGGER.error("save profile failed", e);
            throw new ServiceException(e);
        }
    }


}