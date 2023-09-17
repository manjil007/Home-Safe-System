package homesafe.dao;

import homesafe.entity.User;

import java.sql.Connection;
import java.util.Collections;

import java.util.List;

/**
 * Standard SQLite implementation of {@link UserDAO}
 */
public class UserSQLiteDAO extends AbstractSQLiteDAO implements UserDAO {

    public UserSQLiteDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void initialSetup() {
        // todo
    }

    @Override
    public List<User> listAllUsers() {
        return Collections.emptyList();
    }

}
