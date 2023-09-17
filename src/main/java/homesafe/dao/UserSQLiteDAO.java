package homesafe.dao;

import homesafe.entity.User;

import java.sql.Connection;
import java.util.Collections;

import java.util.List;

public class UserSQLLiteDAO extends AbstractSQLLiteDAO implements UserDAO {

    public UserSQLLiteDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<User> listAllUsers() {
        return Collections.emptyList();
    }

}
