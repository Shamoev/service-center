package services;

import jdbc.exceptions.GenericDaoException;
import jdbc.manager.AbstractManagerFactory;
import jdbc.manager.UserManager;
import jdbc.model.User;

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
