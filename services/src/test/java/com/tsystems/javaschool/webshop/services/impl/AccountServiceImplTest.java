package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.UsersDAO;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.services.exceptions.AccountServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 03.04.2016.
 */
public class AccountServiceImplTest {

    private AccountServiceImpl accountService;
    private String email;
    private String newEmail;
    private String password;
    private List<User> users;
    private User user;
    private User credentials;

    private int userId;

    @Mock
    private UsersDAO usersDAO;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        userId = 1;
        users = new ArrayList<>();
        user = new User();
        credentials = new User();
        email = "asd@mail.ru";
        newEmail = "qwe@mail.ru";
        password = "12345678";
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl();
        accountService.setUsersDAO(usersDAO);
        accountService.setPasswordEncoder(passwordEncoder);
    }

    @Test
    public void getUserByEmailSuccess() {
        when(usersDAO.getUserByEmail(email)).thenReturn(user);
        assertEquals(accountService.getUserByEmail(email), user);
    }

    @Test
    public void saveProfileSuccess() {
        accountService.saveProfile(user);
        verify(usersDAO).update(user);
    }

    @Test
    public void saveCredentialsSuccess() {

        credentials.setEmail(newEmail);
        credentials.setPassword(password);
        user.setEmail(email);
        user.setPassword(password);
        when(usersDAO.getUserByEmail(email)).thenReturn(user);
        when(passwordEncoder.encode(password)).thenReturn(password);

        User savedUser = accountService.saveCredentials(credentials, email);
        verify(usersDAO).update(user);
        assergitEquals(savedUser, user);
    }

    @Test
    public void getAllSuccess() throws Exception {
        users.add(user);
        when(usersDAO.getAll()).thenReturn(users);
        assertEquals(users,
                accountService.getAll());
    }

    @Test
    public void setUserRightsSuccess() {
        when(usersDAO.getById(userId)).thenReturn(user);

        accountService.setUserRights(userId, UserRole.ROLE_ADMIN);
        assertEquals(user.getRole(), UserRole.ROLE_ADMIN);
    }
}
//    @Test
//    public void signUpUserSuccess() {
//        user.setEmail(email);
//        user.setPassword(password);
//        when(usersDAO.getUserByEmail(email)).thenReturn(null);
//        when(passwordEncoder.encode(password)).thenReturn(password);
//
//        User result = accountService.signUpUser(user);
//        verify(usersDAO).create(user);
//        assertEquals(result, user);
//    }
//
//    @Test(expected = AccountServiceException.class)
//    public void signUpUserEmailExistsFail() {
//        user.setEmail(email);
//        user.setPassword(password);
//        when(usersDAO.getUserByEmail(email)).thenReturn(user);
//
//        accountService.signUpUser(user);
//    }
//
//    @Test
//    public void signInUserSuccess() {
//        user.setEmail(email);
//        user.setPassword(password);
//        when(usersDAO.getUserByEmail(email)).thenReturn(user);
//
//        accountService.signInUser(email, password);
//    }
//
//    @Test(expected = AccountServiceException.class)
//    public void signInUserNotExistsFail() {
//        user.setEmail(email);
//        user.setPassword(password);
//        when(usersDAO.getUserByEmail(email)).thenReturn(user);
//
//        accountService.signInUser(email, password);
//    }