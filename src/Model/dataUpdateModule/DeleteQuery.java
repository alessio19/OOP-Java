package Model.dataUpdateModule;

import Model.dataAccessModule.DBConnection;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public class DeleteQuery extends Query {

    public DeleteQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean deleteCustomer(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Customer WHERE idCustomer = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deleteCustomer(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Customer WHERE mail = ? AND password = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteEmployee(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Employee WHERE idEmployee = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deleteEmployee(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Employee WHERE mail = ? AND password = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteMovie(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Movie WHERE idMovie = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteMovie(String title, String author, String releaseDate) throws SQLException {
        String[] param = {title, author, releaseDate};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Movie WHERE title = ? AND author = ? AND releaseDate = ?;");
        return this.executeQuery(param);
    }
    
    public boolean deletePayment(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Payment WHERE idPayment = ?;");
        return this.executeQuery(param);
    } 
    
    public boolean deleteOrder(String id) throws SQLException {
        String[] param = {id};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM Order WHERE idOrder = ?;");        
        return this.executeQuery(param);
    } 

    @Override
    protected boolean executeQuery(String[] param) throws SQLException {
        super.prepareStatement(param);
        return this.statement.execute();
    }

}

