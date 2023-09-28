package homesafe.entity;

/**
 * Data Transfer Object containing user data from the {@code users} table.
 */
public class User {
    private final String username;
    private String hashedPIN;
    private boolean admin;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPIN() {
        return hashedPIN;
    }

    public void setHashedPIN(String secretPIN) {
        this.hashedPIN = secretPIN;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedPIN='" + hashedPIN + '\'' +
                ", admin=" + admin +
                '}';
    }
}
