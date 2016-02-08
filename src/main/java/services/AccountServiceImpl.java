package services;

import dao.User;
import dao.UsersDAO;

/**
 * Created by Shide on 08.02.2016.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void createUser(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        UsersDAO usersDAO = new UsersDAO()
    }
}
