package Model.employee;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 * details: DAO for the table Employee in the database, manage the insertion / selection of different employee in the DB
 */
public class EmployeeDAO {
    
    private Connection connection;  
    
    /**
     * Constructor
     */
    public EmployeeDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Insert an employee in the database
     * @param employee
     * @return boolean: result
     */
    public boolean addEmployee(Employee employee) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Employee (mail, password, name, lastname, profilePicture) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, employee.getMail());
            preparedStatement.setString(2, employee.password);
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setString(5, employee.getProfilePicture());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    /**
     * Retrieve all the employee in the database
     * @return ArrayList of employee: result
     */
    public ArrayList<Employee> getEmployees() {
        ResultSet result = null;
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            result = connection.createStatement().executeQuery("SELECT * FROM Employee");
            while (result.next()) {                
                employees.add(new Employee(
                        result.getInt("idEmployee"),
                        result.getString("mail"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("lastName"),
                        result.getString("profilePicture")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    /**
     * Retrieve an employee by its email and password
     * @param mail
     * @param password
     * @return Employee: result
     */
    public Employee getEmployeeByCredentials(String mail, String password) {
        Employee employee = null;
        try {
            ResultSet result  = connection.createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "' AND password = '" + password + "';");
            result.next();
            employee = new Employee(
                result.getInt("idEmployee"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName"),
                result.getString("profilePicture")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
    /**
     * Find out if the database contain an employee based on an email and pwd
     * @param mail
     * @param password
     * @return boolean: result
     */
    public boolean hasEmployee(String mail, String password) {
        ResultSet result = null;
        try {
             result = connection.createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "' AND password = '" + password + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Find out if an employee exist with this email
     * @param mail
     * @return boolean: result
     */
    public boolean hasEmployee(String mail) {
        ResultSet result = null;
        try {
             result = connection.createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieve an employee by its email
     * @param mail
     * @return Employee: result
     */
    public Employee getEmployeeByMail(String mail) {
        ResultSet result = null;
        Employee employee = null;
        try {
            result = connection.createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "';");
            employee = new Employee(
                result.getInt("idCustomer"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName"),
                result.getString("profilePicture")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
}
