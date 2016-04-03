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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
     * The Authentication manager.
     */
    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;


    /**
     * The Password encoder.
     */
    @Autowired
    @Qualifier(value = "encoder")
    private PasswordEncoder passwordEncoder;

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
    public final User saveCredentials(final User credentials,
                                      final String oldEmail)
            throws AccountServiceException {
        User user = usersDAO.getUserByEmail(oldEmail);
        if (!credentials.getEmail().equals(user.getEmail())
                && usersDAO.getUserByEmail(credentials.getEmail()) != null) {
            throw new AccountServiceException("Email has already taken");
        }
        user.setEmail(credentials.getEmail());
        user.setPassword(passwordEncoder.encode(credentials.getPassword()));
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

    /**
     * Do auto login.
     *
     * @param username the username
     * @param password the password
     * @param details   the detils
     * @throws AuthenticationException the authentication exception
     */
    public final void doAutoLogin(final String username,
                                  final String password,
                                  final WebAuthenticationDetails details)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(details);
        Authentication authentication =
                authenticationManager.authenticate(token);
        //LOGGER.debug("Logging in with " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Sets users dao.
     *
     * @param dao the dao
     */
    public final void setUsersDAO(final UsersDAO dao) {
        this.usersDAO = dao;
    }

    /**
     * Sets authentication manager.
     *
     * @param manager the manager
     */
    public final void setAuthenticationManager(
            final AuthenticationManager manager) {
        this.authenticationManager = manager;
    }

    /**
     * Sets password encoder.
     *
     * @param encoder the encoder
     */
    public final void setPasswordEncoder(
            final PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }
}
