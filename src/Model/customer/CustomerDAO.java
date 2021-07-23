package Model.customer;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class CustomerDAO {
    
    private Connection connection;  
    
    public CustomerDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addCustomer(Customer customer) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Customer (mail, password, name, lastname, memberType, profilePicture) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, customer.getMail());
            preparedStatement.setString(2, customer.password);
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setInt(5, customer.getMemberType().ordinal()+1);
            preparedStatement.setString(6, customer.getProfilePicture());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public boolean updateCustomer(Customer customer) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("UPDATE Customer SET mail = ?, password = ?, name = ?, lastName = ?, memberType = ? WHERE idCustomer = ?;");
            preparedStatement.setString(1, customer.getMail());
            preparedStatement.setString(2, customer.password);
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setInt(5, customer.getMemberType().ordinal()+1);
            preparedStatement.setInt(6, customer.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public Customer getCutomerById(int id) {
        Customer  customer = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Customer WHERE idCustomer = "+id+";");            
            result.next();
            customer = new Customer(
                    result.getInt("idCustomer"),
                    result.getString("mail"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("lastName"),
                    MemberType.values()[result.getInt("memberType")-1],
                    result.getString("profilePicture")
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
            result = connection.createStatement().executeQuery("SELECT * FROM Customer");
            while (result.next()) {                
                customers.add(new Customer(
                        result.getInt("idCustomer"),
                        result.getString("mail"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("lastName"),
                        MemberType.values()[result.getInt("memberType")-1],
                        result.getString("profilePicture")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public Customer getCustomerByCredentials(String mail, String password) {
        Customer customer = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "' AND password = '" + password + "';");
            if (result.next()) {
             customer = new Customer(
                result.getInt("idCustomer"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName"),
                MemberType.values()[result.getInt("memberType")-1],
                result.getString("profilePicture")
            );   
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customer;
    }
    
    public boolean hasCustomer(String mail, String password) {
        ResultSet result = null;
        try {
             result = connection.createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "' AND password = '" + password + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean hasCustomer(String mail) {
        ResultSet result = null;
        try {
             result = connection.createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '" + mail + "';");
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
            result = connection.createStatement().executeQuery("SELECT * FROM Customer WHERE mail = '"+mail+"';");
            customer = new Customer(
                    result.getInt("idCustomer"),
                    result.getString("mail"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("lastName"),
                    MemberType.values()[result.getInt("memberType")-1],
                    result.getString("profilePicture")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customer;
    }
    
    public void deleteCustomer(Customer customer) {
         try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE idCustomer = ?");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
