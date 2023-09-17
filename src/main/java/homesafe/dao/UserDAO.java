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
     * Method to obtain a list of all registered users of the home safe.
     * @return returns a list of {@link User} DTOs
     */
    List<User> listAllUsers() throws SQLException;
}
