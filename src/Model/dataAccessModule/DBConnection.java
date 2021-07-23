package Model.dataAccessModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public final class DBConnection {
    
    private static Connection connection;
    
    static {
        new DBConnection();
    }
    
    private DBConnection() {
        try {
            System.out.println("Connecting to DB");
            this.connection = DriverManager.getConnection("jdbc:mysql://bzqgkdrr20w50frr6kwb-mysql.services.clever-cloud.com/bzqgkdrr20w50frr6kwb", "unmpkgkqj3s0ruuw", "Kcn3nZaUOjGH99Pjj953");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the connection for the DB
     * @return Connection
     */
    public final static Connection getConnection() {
        return connection;
    }

}