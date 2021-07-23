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
 * details: DAO for the table Customer in the database, manage the insertion / update / selection of different customer in the DB
 */
public class CustomerDAO {
    
    private Connection connection;  
    
    /**
     * Constructor
     */
    public CustomerDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Insert a customer in the database
     * @param customer
     * @return boolean: result
     */
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
    
    /**
     * Update a customer in the database
     * @param customer
     * @return boolean: result
     */
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
    
    /**
     * Retrieve a customer by its ID
     * @param id
     * @return Customer: result
     */
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
    
    /**
     * Retrieve all the customers
     * @return ArrayList of Customer: result
     */
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
    
    /**
     * Retrieve a customer based on its email and pwd
     * @param mail
     * @param password
     * @return Customer: result
     */
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
    
    /**
     * Find out if a customer exist based on an email and pwd
     * @param mail
     * @param password
     * @return boolean: result
     */
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
    
    /**
     * Find out if a customer exist based on an email
     * @param mail
     * @return boolean: result
     */
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
    
    /**
     * Retrieve a customer based on its email
     * @param mail
     * @return Customer: result
     */
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
