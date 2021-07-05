package Model.payment;

import Model.dataAccessModule.DBConnection;
import Model.customer.CustomerDAO;
import Model.product.MovieDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class OrderDAO {
    
    private DBConnection connection;  
    
    public OrderDAO() {
        this.connection = new DBConnection();
    }
    
    public boolean addOrder(Order order) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO Order (customerId, productId, quantity, paymentId) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setInt(2, order.getProduct().getId());
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
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Order");
            while (result.next()) {                
                orders.add(new Order(
                        result.getInt("idOrder"),
                        new CustomerDAO().getCutomerById(result.getInt("customerId")),
                        new MovieDAO().getMovieById(result.getInt("productid")),
                        new PaymentDAO().getPaymentById(result.getInt("paymentId")),
                        result.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
