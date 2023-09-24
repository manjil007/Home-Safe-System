package homesafe.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Data Transfer Object containing data to be logged to or returned from the
 * {@code Log} table.
 */
public class LogData {
    private final LocalDateTime createdAt;
    private final String username;
    private final String message;

    public LogData(String username, String message) {
        this(null, username, message);
    }

    public LogData(LocalDateTime createdAt, String username, String message) {
        this.createdAt = Objects.requireNonNullElseGet(createdAt, LocalDateTime::now);
        this.username = username;
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
