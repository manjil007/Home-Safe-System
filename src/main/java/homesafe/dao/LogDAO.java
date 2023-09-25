package homesafe.dao;

import homesafe.entity.LogData;
import homesafe.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * created by:
 * author: MichaelMillar
 */
public interface LogDAO extends BaseDAO {

    /**
     * This method attempts to create the database log table. If the database
     * is already setup, this method will not do anything.
     */
    void initialSetup();

    /**
     * Saves the {@link LogData} DTO to the log table.
     * @param data log data to persist
     * @throws SQLException if update could not be completed
     */
    void log(LogData data) throws SQLException;

    /**
     * Method to obtain a list of all logged events of the home safe.
     * @return returns a list of {@link LogData} DTOs
     * @throws SQLException if the query cannot be completed
     */
    List<LogData> listAllLogs() throws SQLException;

    /**
     * Method to obtain a list of all logged events of the home safe by a
     * specific username.
     *
     * @param username username of logs to return
     * @return         list of logs by the username
     * @throws SQLException if the query cannot be completed
     */
    List<LogData> listAllLogsByUser(String username) throws SQLException;

}
