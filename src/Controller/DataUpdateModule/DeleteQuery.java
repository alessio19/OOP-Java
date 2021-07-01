/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.DataUpdateModule;

import Controller.DataAccessModule.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public class DeleteQuery extends Query {

    public DeleteQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean deleteCustomer(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM customer WHERE idCustomer = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deleteCustomer(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM customer WHERE mail = ? AND password = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteEmployee(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM employee WHERE idEmployee = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deleteEmployee(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM employee WHERE mail = ? AND password = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteMovie(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM movie WHERE idMovie = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteMovie(String title, String author, String releaseDate) throws SQLException {
        String[] param = {title, author, releaseDate};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM movie WHERE title = ? AND author = ? AND releaseDate = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deletePayment(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM payment WHERE idPayment = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteOrder(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM order WHERE idOrder = ?;");        
        return this.executeQuery(param);
    } 

    @Override
    protected boolean executeQuery(String[] param) throws SQLException {
        super.prepareStatement(param);
        return this.statement.execute();
    }

}

