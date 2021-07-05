package Model.customer;

import Model.dataAccessModule.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class CustomerDAO {
    
    private DBConnection connection;  
    
    public CustomerDAO() {
        this.connection = new DBConnection();
    }
    
    public boolean addCustomer(Customer customer) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO Customer (mail, password, name, lastname, memberType) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, customer.getMail());
            preparedStatement.setString(2, customer.password);
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setInt(5, customer.getMemberType().ordinal()+1);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public Customer getCutomerById(int id) {
        ResultSet result = null;
        Customer  customer = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer WHERE idCustomer = "+id+";");
            customer = new Customer(
                    result.getInt("idCustomer"),
                    result.getString("mail"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("lastName"),
                    MemberType.values()[result.getInt("memberType")-1]
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customer;
    }
    
    public ArrayList<Customer> getCutomers() {
        ResultSet result = null;
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer");
            while (result.next()) {                
                customers.add(new Customer(
                        result.getInt("idCustomer"),
                        result.getString("mail"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("lastName"),
                        MemberType.values()[result.getInt("memberType")-1]
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public Customer getCustomerByCredentials(String mail, String password) {
        ResultSet result = null;
        Customer customer = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "' AND password = '" + password + "';");
            customer = new Customer(
                result.getInt("idCustomer"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName"),
                MemberType.values()[result.getInt("memberType")-1]
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customer;
    }
    
    public boolean hasCustomer(String mail, String password) {
        ResultSet result = null;
        try {
             result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "' AND password = '" + password + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean hasCustomer(String mail) {
        ResultSet result = null;
        try {
             result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Customer getCustomerByMail(String mail) {
         ResultSet result = null;
        Customer  customer = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Customer WHERE mail = "+mail+";");
            customer = new Customer(
                    result.getInt("idCustomer"),
                    result.getString("mail"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("lastName"),
                    MemberType.values()[result.getInt("memberType")-1]
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customer;
    }
    
}
