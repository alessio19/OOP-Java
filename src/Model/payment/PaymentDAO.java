package Model.payment;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Alessio
 * @author Adam
 */
public class PaymentDAO {
    
    private Connection connection;
    
    public PaymentDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addPayment(Payment payment) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Payment (price, cardNumber, expirationDate, ccv, paymentStatusId) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setDouble(1, payment.getPrice());
            preparedStatement.setString(2, payment.getCardNumber());
            preparedStatement.setTimestamp(3, new Timestamp(payment.getExpirationDate().getTime()));
            preparedStatement.setString(4, payment.ccv);
            preparedStatement.setInt(5, PaymentSatus.values()[payment.getStatus().ordinal()+1].ordinal());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public Payment getPaymentById(int id) {
        Payment payment = null;
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Payment WHERE idPayment = "+id+";"); 
            result.next();
            payment = new Payment(
                    result.getInt("idPayment"),
                    PaymentSatus.values()[result.getInt("paymentStatusId")],
                    result.getDouble("price"),
                    result.getString("cardNumber"),
                    result.getDate("expirationDate"),
                    result.getString("ccv")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
