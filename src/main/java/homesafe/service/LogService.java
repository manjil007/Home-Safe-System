package homesafe.service;

import homesafe.dao.DAOFactory;
import homesafe.dao.LogDAO;
import homesafe.entity.LogData;
import homesafe.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * created by:
 * author: MichaelMillar
 */
public class LogService {
    private static final LogDAO logDAO;

    private LogService() { /* static only */ }

    static {
        logDAO = DAOFactory.create(LogDAO.class);
    }

    /**
     * Takes in a message and logs it to the log database table
     * @param message log message
     */
    public static void log(String message) {
        // get current authorized user, even when authentication is denied
        // we should have an "attempted" user which we can collect a username
        // from.
        User currentUser = AuthenticationService.getCurrentUser();
        LogData data = new LogData(currentUser.getUsername(), message);
        try {
            logDAO.log(data);
        } catch (SQLException e) {
            // do nothing
        }
    }

    /**
     * Returns a list of all log data from the log database table
     * @return list of log data
     */
    public static List<LogData> fetchAllLogs() {
        try {
            return Objects.requireNonNullElse(logDAO.listAllLogs(), new ArrayList<>());
        } catch (SQLException e) {
            // do nothing
        }
        return new ArrayList<>();
    }

    /**
     * Returns a list of all log data for a specified username from the log
     * database table
     * @param username username to query by
     * @return         list of all log data for username
     */
    public static List<LogData> fetchAllLogsByUsername(String username) {
        try {
            return Objects.requireNonNullElse(logDAO.listAllLogsByUser(username),
                    new ArrayList<>());
        } catch (SQLException e) {
            // do nothing
        }
        return new ArrayList<>();
    }
}
