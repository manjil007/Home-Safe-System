package homesafe.entity;

import java.time.LocalDateTime;

/**
 * Data Transfer Object containing user data from the {@code USER} table.
 */
public class User {

    private final String username;
    private String secretPIN;
    private LocalDateTime expiration;
    private boolean admin;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getSecretPIN() {
        return secretPIN;
    }

    public void setSecretPIN(String secretPIN) {
        this.secretPIN = secretPIN;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
