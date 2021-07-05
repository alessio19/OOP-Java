package Model.employee;

import Model.dataAccessModule.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class EmployeeDAO {
    
    private DBConnection connection;  
    
    public EmployeeDAO() {
        this.connection = new DBConnection();
    }
    
    public boolean addEmployee(Employee employee) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO Employee (mail, password, name, lastname) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, employee.getMail());
            preparedStatement.setString(2, employee.password);
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public ArrayList<Employee> getEmployees() {
        ResultSet result = null;
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Employee");
            while (result.next()) {                
                employees.add(new Employee(
                        result.getInt("idEmployee"),
                        result.getString("mail"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("lastName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    public Employee getEmployeeByCredentials(String mail, String password) {
        ResultSet result = null;
        Employee employee = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "' AND password = '" + password + "';");
            employee = new Employee(
                result.getInt("idCustomer"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
    public boolean hasEmployee(String mail, String password) {
        ResultSet result = null;
        try {
             result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "' AND password = '" + password + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean hasEmployee(String mail) {
        ResultSet result = null;
        try {
             result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "';");
             return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Employee getEmployeeByMail(String mail) {
        ResultSet result = null;
        Employee employee = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Employee WHERE mail = '" + mail + "';");
            employee = new Employee(
                result.getInt("idCustomer"),
                result.getString("mail"),
                result.getString("password"),
                result.getString("name"),
                result.getString("lastName")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
}
