package homesafe.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO {

    /**
     * Commit the transaction and log any exception.
     */
    void commit();

    /**
     * Rollback the transaction and log any exception.
     */
    void rollback();

}
