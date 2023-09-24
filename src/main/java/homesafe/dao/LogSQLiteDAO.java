package homesafe.dao;

import homesafe.entity.LogData;
import homesafe.entity.User;
import homesafe.service.AuthenticationService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

/**
 * created by:
 * author: MichaelMillar
 */
public class LogSQLiteDAO extends AbstractSQLiteDAO implements LogDAO {

    private static final String CREATE_LOG_TABLE
            = "CREATE TABLE IF NOT EXISTS logs (\n"
            + "             username TEXT PRIMARY KEY,\n"
            + "             created_at DATETIME NOT NULL,\n"
            + "             message TEXT NOT NULL\n"
            + ");";

    private static final String ALL_LOGS_QUERY
            = "select /* ALL_LOGS */\n"
            + "          username,\n"
            + "          created_at,\n"
            + "          message\n"
            + "from      logs;";

    private static final String ALL_LOGS_FOR_USERNAME_QUERY
            = ALL_LOGS_QUERY.replace(';', '\n')
            .concat("where username = ?;");

    public LogSQLiteDAO(Connection conn) {
        super(conn);
    }

    private static Logger getLogger() {
        return Logger.getLogger(LogSQLiteDAO.class.getName());
    }

    private static LogData mapLogData(ResultSet rs) throws SQLException {
        String username = rs.getString(1);
        LocalDateTime createdAt = DAOUtils.getLocalDateTime(rs, 2);
        String message = rs.getString(3);

        return new LogData(createdAt, username, message);
    }

    @Override
    public void initialSetup() {
        try (Connection conn = DAOUtils.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(CREATE_LOG_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().log(WARNING, "[LogSQLiteDAO] failed to initialize log table. {0}",
                    e.getMessage().trim());
        }
    }

    @Override
    public List<LogData> listAllLogs() throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            List<LogData> result = DAOUtils.queryForList(conn,
                    ALL_LOGS_QUERY,
                    null,
                    LogSQLiteDAO::mapLogData);

            long dur = System.currentTimeMillis() - start;
            getLogger().log(INFO, "[SQLStats] ALL_LOGS_QUERY {0} row(s) in {1} ms.",
                    new Object[]{result.size(), dur});
            return result;
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] ALL_LOGS_QUERY failed({0}) in {1} ms.",
                    new Object[]{e.getMessage().trim(), dur});
            throw e;
        }
    }

    @Override
    public List<LogData> listAllLogsByUser(String username) throws SQLException {
        long start = System.currentTimeMillis();

        try (Connection conn = DAOUtils.getConnection()) {
            List<LogData> result = DAOUtils.queryForList(conn,
                    ALL_LOGS_FOR_USERNAME_QUERY,
                    ps -> ps.setString(1, username),
                    LogSQLiteDAO::mapLogData);

            long dur = System.currentTimeMillis() - start;
            getLogger().log(INFO, "[SQLStats] ALL_LOGS_BY_USERNAME[{0}] " +
                    " {1} row(s) in {2} ms.", new Object[]{username, result.size(), dur});
            return result;
        } catch (SQLException e) {
            long dur = System.currentTimeMillis() - start;
            getLogger().log(WARNING, "[SQLStats] ALL_LOGS_BY_USERNAME[{0}] " +
                    " failed({1}) in {2} ms.", new Object[]{username, e.getMessage().trim(), dur});
            throw e;
        }
    }
}
