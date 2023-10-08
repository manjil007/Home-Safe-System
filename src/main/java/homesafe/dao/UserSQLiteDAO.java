package homesafe.dao;

import homesafe.entity.User;
import homesafe.service.AuthenticationService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
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
            + "       admin INTEGER NOT NULL\n"
            + ");";

    /**
     * SQL query to obtain all users from the {@code users} table.
     */
    private static final String ALL_USERS_QUERY
            = "select /* ALL_USERS_QUERY */\n"
            + "          name,\n"
            + "          hashed_pin,\n"
            + "          admin\n"
            + "from      users;";

    private static final String FIND_USER_BY_NAME_QUERY
            = "select /* USER_BY_NAME */\n"
            + "          name,\n"
            + "          hashed_pin,\n"
            + "          admin\n"
            + "from      users\n"
            + "where     name = ?";

    /**
     * SQL query to insert a new user into the {@code users} table
     */
    private static final String NEW_USER_QUERY
            = "insert into users( /* NEW_USER */\n"
            + "            name,\n"
            + "            hashed_pin,\n"
            + "            admin)\n"
            + "values (?, ?, ?);";

    /**
     * SQL query to update a user from the {@code users} table
     */
    private static final String UPDATE_USER_QUERY
            = "update users SET /* UPDATE_USER */\n"
            + "             hashed_pin = ?,\n"
            + "             admin = ?\n"
            + "where        name = ?;";

    /**
     * SQL query to remove a user from the {@code users} table
     */
    private static final String REMOVE_USER_QUERY
            = "delete from users /* REMOVE_USER */\n"
            + "where       name = ?;";

    private static final String USER_COUNT_QUERY
            = "select count(*) as total from users;";

    public UserSQLiteDAO(Connection conn) {
        super(conn);
        initialSetup();
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

        return user;
    }

    @Override
    public void initialSetup() {
        try (Connection conn = DAOUtils.getConnection()) {
            ResultSet rs = conn.getMetaData().getTables(null, null, "users", new String[] {"TABLE"});
            if (!rs.next()) { // table does not exist
                PreparedStatement ps = conn.prepareStatement(CREATE_USER_TABLE);
                ps.execute();
            }

            rs = conn.getMetaData().getTables(null, null, "users", new String[] {"TABLE"});
            if (rs.next()) {
                getLogger().log(INFO, "[SQLStats] User table successfully created.");

                PreparedStatement ps = conn.prepareStatement(USER_COUNT_QUERY);
                rs = ps.executeQuery();
                int userCount = rs.getInt("total");

                if (userCount == 0) {
                    User defaultUser = new User("admin");
                    defaultUser.setHashedPIN(AuthenticationService.hashPIN("admin", "000000"));
                    defaultUser.setAdmin(true);

                    addUser(defaultUser);
                }
            }
        } catch (SQLException e) {
            getLogger().log(WARNING, "[SQLStats] User table failed to be created. {0}",
                    e.getMessage().trim());
        }
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(NEW_USER_QUERY);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getHashedPIN());
            ps.setInt(3, user.isAdmin() ? 1 : 0);
            int result = ps.executeUpdate();

            long dur = System.currentTimeMillis() - start;
            if (result == 1) {
                getLogger().log(INFO, "[SQLStats] Successfully added user to table in {0} ms.", dur);
                return true;
            }
            getLogger().log(WARNING, "[SQLStats] Failed to add user to table in {0} ms.", dur);
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] Failed to add user to table in {0} ms. {1}",
                    new Object[]{dur, e.getMessage().trim()});
            throw e;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER_QUERY);
            ps.setString(1, user.getHashedPIN());
            ps.setInt(2, user.isAdmin() ? 1 : 0);
            ps.setString(3, user.getUsername());
            int result = ps.executeUpdate();

            long dur = System.currentTimeMillis() - start;
            if (result == 1) {
                getLogger().log(INFO, "[SQLStats] Successfully updated user in {0} ms.", dur);
                return true;
            }
            getLogger().log(WARNING, "[SQLStats] Failed to update user in {0} ms.", dur);
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] Failed to update user in {0} ms. {1}",
                    new Object[]{dur, e.getMessage().trim()});
            throw e;
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(REMOVE_USER_QUERY);
            ps.setString(1, user.getUsername());
            int result = ps.executeUpdate();

            long dur = System.currentTimeMillis() - start;
            if (result == 1) {
                getLogger().log(INFO, "[SQLStats] Successfully removed user from table in {0} ms", dur);
                return true;
            }
            getLogger().log(WARNING, "[SQLStats] Failed to remove user from table in {0} ms.", dur);
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] Failed to remove user from table in {0} ms. {1}",
                    new Object[]{dur, e.getMessage().trim()});
            throw e;
        }
        return false;
    }

    @Override
    public List<User> listAllUsers() throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            List<User> result = DAOUtils.queryForList(conn,
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

    @Override
    public Optional<User> getUserByName(String name) throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(FIND_USER_BY_NAME_QUERY);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return Optional.of(mapUser(rs));
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] USER_BY_NAME failed({0}) in {1} ms.",
                    new Object[]{e.getMessage().trim(), dur});
            throw e;
        }
    }
}
