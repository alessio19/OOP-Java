package Model.payment;

import Model.dataAccessModule.DBConnection;
import Model.customer.CustomerDAO;
import Model.filmSession.FilmSessionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alessio
 * @author Adam
 * details: DAO for the table Order in the database, manage the insertion / selection of different order in the DB
 */
public class OrderDAO {
    
    private Connection connection;  
    
    /**
     * Constructor
     */
    public OrderDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Insert an order in the database
     * @param order
     * @return boolean: result
     */
    public boolean addOrder(Order order) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Orders (customerId, productId, quantity, paymentId) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setInt(2, order.getProduct().getIdFilmSession());
            preparedStatement.setInt(3, order.getIquantity());
            preparedStatement.setInt(4, order.getPayment().getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    /**
     * Retrieve all the orders
     * @return ArrayList of order: result
     */
    public ArrayList<Order> getOrders() {
        ResultSet result = null;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            result = connection.createStatement().executeQuery("SELECT * FROM Orders");
            while (result.next()) {                
                orders.add(new Order(
                        result.getInt("idOrder"),
                        new CustomerDAO().getCutomerById(result.getInt("customerId")),
                        new FilmSessionDAO().getFilmSessionById((result.getInt("productid"))),
                        new PaymentDAO().getPaymentById(result.getInt("paymentId")),
                        result.getInt("quantity"),
                        result.getDate("orderDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    /**
     * Retrieve all the dates of all the orders
     * @return ArrayList of date: dates
     */
    public ArrayList<Date> getDates() {        
        ArrayList<Date> dates = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Orders");
            while (result.next()) {                
                dates.add(result.getDate("orderDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }     
    
    /**
     * Retrieve the orders of a user
     * @param id
     * @return ArrayList of order: result
     */
    public ArrayList<Order> getOrdersForUsrId(int id) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Orders WHERE customerId = "+id+";");
            while (result.next()) {                
                orders.add(new Order(
                        result.getInt("idOrder"),
                        new CustomerDAO().getCutomerById(result.getInt("customerId")),
                        new FilmSessionDAO().getFilmSessionById((result.getInt("productid"))),
                        new PaymentDAO().getPaymentById(result.getInt("paymentId")),
                        result.getInt("quantity"),
                        result.getDate("orderDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
   
    /**
     * Retrieve the order
     * @param order
     */
    public void deleteOrder(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Orders WHERE idOrder = ?");
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
