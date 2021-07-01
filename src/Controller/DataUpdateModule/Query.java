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
	
    public Query(DBConnection connection) throws SQLException {
        this.connection = connection;
    }
	
    public void prepareStatement(String[] param) throws SQLException {
        for (int i = 1; i <= param.length; ++i)
            this.statement.setString(i, param[i-1]);
    }
	
    protected abstract boolean executeQuery() throws SQLException;

}