package homesafe.dao;

public abstract class AbstractSQLLiteDAO implements BaseDAO {

    /**
     * SQL to retrieve column names for a given table
     * As we build out functionality to automate reconciling duplicate ids it is important for
     * the DAOs to be able to query for the column names in order to facilitate generating sql statements
     */
    public static final String COL_NAMES_QUERY
            = "select /* COLUMN_NAMES_QUERY */\n"
            + "        column_name\n"
            + "from all_tab_cols\n"
            + "where table_name = tableName";




}
