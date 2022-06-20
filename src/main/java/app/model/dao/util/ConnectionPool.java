package app.model.dao.util;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static final String DB_RESOURCE_BUNDLE = "db";
    private static final String URL = "db.url";
    private static ConnectionPool instance = null;
    Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
    }

    /**
     * @return only one instance of the class
     */
    public synchronized static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    /**
     * @return object of data source;
     */
    public DataSource getPooledConnectionDataSource() {
        ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setURL(rb.getString(URL));
        return ds;
    }

    /**
     * The method by which we will get the connection,
     * but not directly, but through the connection pool.
     *
     * @return connection from pool connection
     */
    public Connection getConnection() {
        DataSource ds = getPooledConnectionDataSource();
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

}
