package homesafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import static java.util.logging.Level.SEVERE;

/**
 * created by:
 * author: MichaelMillar
 */
public interface DAOUtils {

    static final String JDBC_CONNECTION_STRING = "jdbc:sqlite:safe.db";

    static Logger getLogger() {
        return Logger.getLogger(DAOUtils.class.getName());
    }

    /**
     * Retrive a SQLite database {@link Connection} object. This would normally
     * be done in a context safe way, however for project simplicity we have
     * simplified the process.
     *
     * @return the database connection
     */
    static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(JDBC_CONNECTION_STRING);

            getLogger().log(INFO, "[SQLStats] Connection to SQLite database " +
                    "was successful.");
        } catch (SQLException e) {
            getLogger().log(WARNING, "[SQLStats] Connection to SQLite database" +
                            " was unsuccessful. {0}",
                    e.getMessage().trim());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                getLogger().log(SEVERE, "[SQLStats] Critical failure when attempting" +
                        " to close SQLite connection. {0}", e.getMessage().trim());
            }
        }

        return conn;
    }

}
