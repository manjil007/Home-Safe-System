package homesafe.service;

import homesafe.dao.DAOFactory;
import homesafe.dao.UserDAO;
import homesafe.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created by:
 * author: MichaelMillar
 */
public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private final UserDAO userDAO;

    private UserService() {
        this.userDAO = DAOFactory.create(UserDAO.class);
    }

    private Logger getLogger() {
        return Logger.getLogger(UserService.class.getName());
    }

    public Optional<User> getUserByName(String name) {
        try {
            return userDAO.getUserByName(name);
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred fetching user({0}). {1}",
                    new Object[]{name, e.getMessage().trim()});
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.listAllUsers();
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred fetching all users. {0}",
                    e.getMessage().trim());
        }
        return new ArrayList<>();
    }

    public void addUser(User user) {
        try {
            boolean result = userDAO.addUser(user);
            if (result) {
                LogService.log(String.format("%s successfully added new user: %s",
                        AuthenticationService.getCurrentUser().getUsername(),
                        user.getUsername()));
            }
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred adding user ({0}). {1}",
                    new Object[]{user.getUsername(), e.getMessage().trim()});
        }
    }

    public void deleteUserByName(String name) {
        deleteUser(new User(name));
    }

    public void deleteUser(User user) {
        try {
            boolean result = userDAO.removeUser(user);
            if (result) {
                LogService.log(String.format("%s successfully deleted user: %s",
                        AuthenticationService.getCurrentUser().getUsername(),
                        user.getUsername()));
            }
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred deleting user ({0}). {1}",
                    new Object[]{user.getUsername(), e.getMessage().trim()});
        }
    }

    public void updateUser(User user) {
        try {
            boolean result = userDAO.updateUser(user);
            if (result) {
                LogService.log(String.format("%s successfully updated user: %s",
                        AuthenticationService.getCurrentUser().getUsername(),
                        user.getUsername()));
            }
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred updating user ({0}). {1}",
                    new Object[]{user.getUsername(), e.getMessage().trim()});
        }
    }

}
