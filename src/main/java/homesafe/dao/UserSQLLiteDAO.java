package homesafe.dao;

import homesafe.entity.User;
import java.util.Collections;

import java.util.List;

public class UserSQLLiteDAO implements UserDAO {

    @Override
    public List<User> listAllUsers() {
        return Collections.emptyList();
    }

}
