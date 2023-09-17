package homesafe.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

public abstract class AbstractSQLiteDAO implements BaseDAO {

    private final Connection conn;

    protected AbstractSQLiteDAO(Connection conn) {
        this.conn = conn;
    }

    private static Logger getLogger() {
        return Logger.getLogger(AbstractSQLiteDAO.class.getName());
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    public void commit() {
        try {
            getConn().commit();
        } catch (SQLException e) {
            getLogger().log(WARNING, "[SQLStats] COMMIT failed: {0}",
                    e.getMessage().trim());
        }
    }

    @Override
    public void rollback() {
        try {
            getConn().rollback();
        } catch (SQLException e) {
            getLogger().log(WARNING, "[SQLStats] ROLLBACK failed: {0}",
                    e.getMessage().trim());
        }
    }
}
