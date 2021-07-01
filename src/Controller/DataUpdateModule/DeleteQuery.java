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
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean deleteCustomer(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM customer WHERE mail = ? AND password = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 
    
    public boolean deleteEmployee(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM employee WHERE idEmployee = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean deleteEmployee(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM employee WHERE mail = ? AND password = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 
    
    public boolean deleteMovie(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM movie WHERE idMovie = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 
    
    public boolean deleteMovie(String title, String author, String releaseDate) throws SQLException {
        String[] param = {title, author, releaseDate};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM movie WHERE title = ? AND author = ? AND releaseDate = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean deletePayment(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM payment WHERE idPayment = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 
    
    public boolean deleteOrder(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM order WHERE idOrder = ?;");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 

    @Override
    protected boolean executeQuery() throws SQLException {
        return this.statement.execute();
    }

}

