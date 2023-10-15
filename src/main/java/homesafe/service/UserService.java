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
 * Service class that handles all the user data requests to and from the
 * UserDAO.
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

    /**
     * Method to fetch a {@link User} from the UserDAO by it's username.
     * @param name username of the user
     * @return an optional that may or may not contain a User
     */
    public Optional<User> getUserByName(String name) {
        try {
            return userDAO.getUserByName(name);
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred fetching user({0}). {1}",
                    new Object[]{name, e.getMessage().trim()});
        }
        return Optional.empty();
    }

    /**
     * Method to fetch all {@link User} from the UserDAO.
     *
     * @return a list of {@link User} objects, or an empty list
     */
    public List<User> getAllUsers() {
        try {
            return userDAO.listAllUsers();
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred fetching all users. {0}",
                    e.getMessage().trim());
        }
        return new ArrayList<>();
    }

    /**
     * Method to persist a {@link User} into the user database.
     *
     * @param user user to store
     */
    public void addUser(User user) {
        // hash user's pin
      //  user.setHashedPIN(AuthenticationService.hashPIN(user.getUsername(), user.getHashedPIN()));
        try {
            boolean result = userDAO.addUser(user);
            if (result) {
                LogService.log(String.format("%s successfully added new user: %s",
                        AuthenticationService.getCurrentUser().getUsername(),
                        user.getUsername()));
            }
        }catch (SQLException e) {
            getLogger().log(Level.WARNING, "[UserService]: Error occurred adding user ({0}). {1}",
                    new Object[]{user.getUsername(), e.getMessage().trim()});
        }
    }

    /**
     * Method to remove a {@link User} from the user database based on it's username.
     *
     * @param name name of user to delete
     */
    public void deleteUserByName(String name) {
        deleteUser(new User(name));
    }

    /**
     * Method to remove a {@link User} from the user database.
     *
     * @param user user to delete
     */
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

    /**
     *  Method to update a {@link User} in the user database.
     *
     * @param user user to update
     */
    public void updateUser(User user) {
        // hash user's pin
     //   user.setHashedPIN(AuthenticationService.hashPIN(user.getUsername(), user.getHashedPIN()));
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
