/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.InformationSearchModule;

import Controller.DataAccessModule.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alessio
 */
public class SelectQuery {

    private final DBConnection connection;
    private final Statement statement;
	
    public SelectQuery(DBConnection connection) throws SQLException {
        this.connection = connection;
	this.statement = this.connection.getConnection().createStatement();
    }
    
    public ResultSet getCustomer() throws SQLException {
        return this.getFromDB("SELECT * FROM customer;");
    }
    
    public ResultSet getCustomer(String mail, String password) throws SQLException {
        String q = "SELECT * FROM customer WHERE mail = '" + mail + "' AND password = '" + password + "';";
        return this.getFromDB(q);
    }
    
    public ResultSet getEmployees() throws SQLException {
        return this.getFromDB("SELECT * FROM employee;");
    }
    
    public ResultSet getEmployee(String mail, String password) throws SQLException {
        String q = "SELECT * FROM employee WHERE mail = '" + mail + "' AND password = '" + password + "';";
        return this.getFromDB(q);
    }
    
    public boolean isEmpty(ResultSet rs) throws SQLException {  
        return !rs.next();
    }
	
    private ResultSet getFromDB(String query) throws SQLException {
        return this.statement.executeQuery(query);
    }
	
}
