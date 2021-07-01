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

    private DBConnection connection;
    private Statement statement;
    private String query;
	
    public SelectQuery(DBConnection connection, String query) throws SQLException {
        this.connection = connection;
	this.statement = this.connection.getConnection().createStatement();
        this.query = query;
    }
	
    public ResultSet getFromDB() throws SQLException {
        return this.statement.executeQuery(this.query);
    }
	
}
