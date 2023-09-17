package homesafe.dao;

import homesafe.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;

import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;
import static java.util.logging.Level.INFO;

/**
 * Standard SQLite implementation of {@link UserDAO}
 */
public class UserSQLiteDAO extends AbstractSQLiteDAO implements UserDAO {

    /**
     * SQL query to create the user database table if it does not yet exist.
     */
    private static final String CREATE_USER_TABLE
            = "CREATE TABLE IF NOT EXISTS users (\n"
            + "       name text TEXT PRIMARY KEY,\n"
            + "       hashed_pin TEXT NOT NULL,\n"
            + "       admin INTEGER NOT NULL,\n"
            + "       expiration_date TEXT"
            + ");";

    /**
     * SQL query to obtain all users from the {@code user} table.
     */
    private static final String ALL_USERS_QUERY
            = "select /* ALL_USERS_QUERY */\n"
            + "          name,\n"
            + "          hashed_pin,\n"
            + "          admin,\n"
            + "          expiration_date\n"
            + "from      users;";

    public UserSQLiteDAO(Connection conn) {
        super(conn);
    }

    private static Logger getLogger() {
        return Logger.getLogger(UserSQLiteDAO.class.getName());
    }

    /**
     * Mapper method that maps a row of the ResultSet into a {@link User} object.
     *
     * @param rs query result row
     * @return the mapped {@link User} object
     * @throws SQLException if obtaining data from ResultSet fails
     */
    private static User mapUser(ResultSet rs) throws SQLException {
        String name = rs.getString(1);
        String hashedPIN = rs.getString(2);
        boolean admin = rs.getInt(3) == 1;
        LocalDateTime expiration = DAOUtils.getLocalDateTime(rs, 4);

        User user = new User(name);
        user.setHashedPIN(hashedPIN);
        user.setAdmin(admin);
        user.setExpiration(expiration);

        return user;
    }

    @Override
    public void initialSetup() {
        try {
            PreparedStatement ps = getConn().prepareStatement(CREATE_USER_TABLE);
            ps.execute();

            getLogger().log(INFO, "[SQLStats] User table successfully created.");
        } catch (SQLException e) {
            getLogger().log(WARNING, "[SQLStats] User table failed to be created. {0}",
                    e.getMessage().trim());
        }
    }

    @Override
    public List<User> listAllUsers() throws SQLException {
        long start = System.currentTimeMillis();

        try {
            List<User> result = DAOUtils.queryForList(getConn(),
                    ALL_USERS_QUERY,
                    null,
                    UserSQLiteDAO::mapUser);

            long dur = System.currentTimeMillis() - start;
            getLogger().log(INFO, "[SQLStats] ALL_USERS {0} row(s) in {1} ms.",
                    new Object[]{result.size(), dur});

            return result;
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] ALL_USERS failed({0}) in {1} ms.",
                    new Object[]{e.getMessage().trim(), dur});
            throw e;
        }
    }

}
