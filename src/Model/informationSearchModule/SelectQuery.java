package Model.informationSearchModule;

import Model.dataAccessModule.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alessio
 * @author Adam
 */
public class SelectQuery {

    private final DBConnection connection;
    private final Statement statement;
	
    public SelectQuery(DBConnection connection) throws SQLException {
        this.connection = connection;
	this.statement = this.connection.getConnection().createStatement();
    }
    
    public ResultSet getCustomer() throws SQLException {
        return this.getFromDB("SELECT * FROM Customer;");
    }
    
    public ResultSet getCustomer(String mail, String password) throws SQLException {
        String q = "SELECT * FROM Customer WHERE mail = '" + mail + "' AND password = '" + password + "';";
        return this.getFromDB(q);
    }
    
    public ResultSet getCustomerByMail(String mail) throws SQLException {
        return this.getFromDB( "SELECT * FROM Customer WHERE mail = '" + mail +  "';");
    } 
    
    public ResultSet getEmployees() throws SQLException {
        return this.getFromDB("SELECT * FROM Employee;");
    }
    
    public ResultSet getEmployee(String mail, String password) throws SQLException {
        String q = "SELECT * FROM Employee WHERE mail = '" + mail + "' AND password = '" + password + "';";
        return this.getFromDB(q);
    }
    
    public ResultSet getEmployeeByMail(String mail) throws SQLException {
         return this.getFromDB("SELECT * FROM Employee where mail = "+mail+";");
    }
    
    public boolean isEmpty(ResultSet rs) throws SQLException {  
        return !rs.next();
    }
    
    
	
    private ResultSet getFromDB(String query) throws SQLException {
        return this.statement.executeQuery(query);
    }
	
}
