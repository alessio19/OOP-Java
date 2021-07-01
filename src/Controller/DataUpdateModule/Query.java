/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.DataUpdateModule;

import Controller.DataAccessModule.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public abstract class Query {
	
    protected DBConnection connection;  
    protected PreparedStatement statement;
	
    public Query(DBConnection connection, String query) throws SQLException {
        this.connection = connection;
	this.statement = this.connection.getConnection().prepareStatement(query);
    }
	
    public void prepareStatement(Object[] param) {
        //TO DO
    }
	
    public abstract boolean executeQuery() throws SQLException;

}