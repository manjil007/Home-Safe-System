package homesafe.dao;

import homesafe.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * A DAO to retrieve user information from a database.
 */
public interface UserDAO extends BaseDAO {

    /**
     * This method attempts to create the database structure and default
     * user if the database has not been previously setup. If the database
     * is already setup, this method will not do anything.
     */
    void initialSetup();

    /**
     * This method attempts to add a new user to the database.
     *
     * @param user {@link User} to add to the {@code users} table.
     * @return true if user was successfully added
     * @throws SQLException if database query could not be executed
     */
    boolean addUser(User user) throws SQLException;

    /**
     * This method attempts to update a new user to the database.
     *
     * @param user {@link User} to update in the {@code users} table.
     * @return true if user was successfully updated
     * @throws SQLException if database query could not be executed
     */
    boolean updateUser(User user) throws SQLException;

    /**
     * This method attempts to add a remove user to the database.
     *
     * @param user {@link User} to remove to the {@code users} table.
     * @return true if user was successfully removed
     * @throws SQLException if database query could not be executed
     */
    boolean removeUser(User user) throws SQLException;

    /**
     * Method to obtain a list of all registered users of the home safe.
     * @return returns a list of {@link User} DTOs
     */
    List<User> listAllUsers() throws SQLException;
}
