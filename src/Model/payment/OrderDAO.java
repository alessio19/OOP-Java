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
 */
public class OrderDAO {
    
    private Connection connection;  
    
    public OrderDAO() {
        this.connection = DBConnection.getConnection();
    }
    
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
