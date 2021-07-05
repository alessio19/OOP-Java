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
    
    private DBConnection() {
        try {
            System.out.println("Connecting to DB");
            this.connection = DriverManager.getConnection("jdbc:mysql://bzqgkdrr20w50frr6kwb-mysql.services.clever-cloud.com/bzqgkdrr20w50frr6kwb", "unmpkgkqj3s0ruuw", "Kcn3nZaUOjGH99Pjj953");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        new DBConnection();
        return connection;
    }

}