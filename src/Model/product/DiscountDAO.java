package Model.product;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public class DiscountDAO {
       private Connection connection;  
    
    public DiscountDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addDiscount(Discount discount){
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Discount (value) VALUES (?);");
            preparedStatement.setDouble(1, discount.getValue());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public Discount getDiscountById(int id) {
        ResultSet result = null;
        Discount  discount = null;
        try {
            result = connection.createStatement().executeQuery("SELECT * FROM Discount WHERE idDiscount = "+id+";");
            discount = new Discount(
                    result.getInt("idDiscount"),
                   result.getDouble("value")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return discount;
    }
}
