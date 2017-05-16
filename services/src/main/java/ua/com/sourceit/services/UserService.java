package ua.com.sourceit.services;

import ua.com.sourceit.jdbc.exceptions.GenericDaoException;
import ua.com.sourceit.jdbc.manager.AbstractManagerFactory;
import ua.com.sourceit.jdbc.manager.UserManager;
import ua.com.sourceit.jdbc.model.User;

public class UserService {
    private UserManager userManager = AbstractManagerFactory.getManagerFactory().getUserManager();

    public User getUserByLogin(String login) {
        try {
            return userManager.getUserByLogin(login);
        } catch (GenericDaoException e) {
            return null;
        }
    }
}
