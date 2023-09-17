package homesafe.service;

import homesafe.entity.User;

/**
 * This class will be responsible for obtaining and authentication user data.
 */
public class UserService {

    /**
     * Utility method that takes a {@link User}'s name and PIN and calculates
     * a hash string from them.
     *
     * @param name the user's name
     * @param pin  the user's PIN
     * @return a hashed string of the user's name and PIN
     */
    public static String hashPIN(String name, String pin) {
        // TODO(mike): Determine how to best hash the name and pin
        return String.format("%s:%s", name, pin);
    }
}
