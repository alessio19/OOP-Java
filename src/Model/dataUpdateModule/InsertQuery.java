package Model.dataUpdateModule;

import Model.dataAccessModule.DBConnection;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public class InsertQuery extends Query {
    
    public InsertQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean insertCustomer(String mail, String password, String name, String lastName, int type) throws SQLException {
        String[] param = {mail, password, name, lastName};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO Customer (mail, password, name, lastname, memberType) VALUES (?, ?, ?, ?, ?);");
        this.statement.setInt(5, type);
        return this.executeQuery(param);
    } 
    
    public boolean insertEmployee(String mail, String password, String name, String lastName) throws SQLException {
        String[] param = {mail, password, name, lastName};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO Employee (mail, password, name, lastname) VALUES (?, ?, ?, ?);");
        return this.executeQuery(param);
    }
    
    public boolean insertMovie(String details, String diffusion, String ticketPrice, String title, String author, String releaseDate, String genre) throws SQLException {
        String[] param = {details, diffusion, ticketPrice, title, author, releaseDate, genre};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO Movie (details, diffusion, ticketPrice, title, author, releaseDate, movieGenre) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        return this.executeQuery(param);
    }
    
    public boolean insertOrder(String idCustomer, String idProduct, String quantity, String idPayment) throws SQLException {
        String[] param = {idCustomer, idProduct, quantity, idPayment};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO Order (customerId, productId, quantity, paymentId) VALUES (?, ?, ?, ?);");
        return this.executeQuery(param);
    }
    
    public boolean insertPayment(String price, String cardNumber, String expirationDate, String cvv, String paymentStatus) throws SQLException {
        String[] param = {price, cardNumber, expirationDate, cvv, paymentStatus};
        this.statement = this.connection.getConnection().prepareStatement("INSERT INTO Payment (price, cardNumber, expirationDate, cvv, paymentStatus) VALUES (?, ?, ?, ?, ?);");
        return this.executeQuery(param);
    }

    @Override
    protected boolean executeQuery(String[] param) throws SQLException {
        super.prepareStatement(param);
	return this.statement.execute();
    }

}
