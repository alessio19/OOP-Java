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
public class InsertQuery extends Query {
    
    public InsertQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean insertCustomer(String mail, String password, String name, String lastName, String type) throws SQLException {
        String[] param = {mail, password, name, lastName, type};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO customer (mail, password, name, lastname, memberType) VALUES (?, ?, ?, ?, ?);");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 
    
    public boolean insertEmployee(String mail, String password, String name, String lastName) throws SQLException {
        String[] param = {mail, password, name, lastName};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO employee (mail, password, name, lastname) VALUES (?, ?, ?, ?);");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean insertMovie(String details, String diffusion, String ticketPrice, String title, String author, String releaseDate, String genre) throws SQLException {
        String[] param = {details, diffusion, ticketPrice, title, author, releaseDate, genre};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO movie (details, diffusion, ticketPrice, title, author, releaseDate, movieGenre) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean insertOrder(String idCustomer, String idProduct, String quantity, String idPayment) throws SQLException {
        String[] param = {idCustomer, idProduct, quantity, idPayment};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO order (customerId, productId, quantity, paymentId) VALUES (?, ?, ?, ?);");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }
    
    public boolean insertPayment(String price, String cardNumber, String expirationDate, String cvv, String paymentStatus) throws SQLException {
        String[] param = {price, cardNumber, expirationDate, cvv, paymentStatus};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO payment (price, cardNumber, expirationDate, cvv, paymentStatus) VALUES (?, ?, ?, ?, ?);");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    }

    @Override
    protected boolean executeQuery() throws SQLException {
	return this.statement.execute();
    }

}
