package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Account service.
 */
@Service(value = "userDetailsService")
@Transactional
public class AccountServiceImpl implements AccountService, UserDetailsService {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AccountService.class);

    /**
     * The Users dao.
     */
    @Autowired
    private UsersDAO usersDAO;

    /**
     * The Password encoder.
     */
    @Autowired
    @Qualifier(value = "encoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public final User signUpUser(final User user)
            throws AccountServiceException {

            if (usersDAO.getUserByEmail(user.getEmail()) != null) {
                throw new AccountServiceException("email already registered");
            }
            //hash password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersDAO.create(user);
            return user;
    }


    @Override
    public final User signInUser(final String email,
                                 final String password)
            throws AccountServiceException {

        User user = usersDAO.getUserByEmail(email);

        if (user == null
                || passwordEncoder.matches(password, user.getPassword())) {
            throw new AccountServiceException(
                    "Wrong password or email");
        }
        return user;
    }
    @Override
    public final User getUser(final int userID) {
        return usersDAO.getById(userID);
    }

    @Override
    public final User getUserByEmail(final String email) {
        return usersDAO.getUserByEmail(email);
    }

    @Override
    public final User saveProfile(final User user)
            throws AccountServiceException {
        usersDAO.update(user);
        return user;
    }

    @Override
    public final List<User> getAll() {
        return usersDAO.getAll();
    }

    @Override
    public final void setUserRights(final int userId, final UserRole isAdmin) {
        usersDAO.getById(userId)
        .setRole(isAdmin);
    }

    /**
     * Custom implementation of {@link UserDetailsService}. <br>
     * {@inheritDoc}
     * @param email instead of username using email
     * @return {@inheritDoc}
     * @throws UsernameNotFoundException {@inheritDoc}
     */
    @Override
    public final UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        User user = usersDAO.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
    }
}
