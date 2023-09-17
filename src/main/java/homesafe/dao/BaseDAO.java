package homesafe.dao;

import java.util.List;

public interface BaseDAO {

    /**
     * TODO
     * @return
     */
    List<String> getColumnsNames();

    void commit();

    void rollback();
}
