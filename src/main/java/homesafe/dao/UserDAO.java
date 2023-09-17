package homesafe.dao;

import homesafe.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> listAllUsers();
}
