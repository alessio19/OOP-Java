/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.DataAccessModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public class DBConnection {
	
    private Connection connection;
    
    public DBConnection(String username, String password) {
        try {
            this.connection = DriverManager.getConnection("jdbc:idbc:OOPJava", username, password);
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }

    public Connection getConnection() {
        return connection;
    }

}