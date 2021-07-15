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
    
    public boolean addDiscount(double value){
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Discount (value) VALUES (?);");
            preparedStatement.setDouble(1, value);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public boolean applyDiscount(Discount discount, Movie movie) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Discount (value) VALUES (?);");
            preparedStatement.setDouble(1, discount.getValue());
            preparedStatement.execute();
            preparedStatement.close();
            
            PreparedStatement ps = connection.prepareStatement("UPDATE Movie SET discountId = ? WHERE idMovie = ? ON UPDATE CASCADE");
            ps.setInt(1, discount.getId());
            ps.setInt(2, movie.getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Discount getDiscountById(int id) {
        Discount  discount = null;
        try {
             ResultSet result = connection.createStatement().executeQuery("SELECT * FROM Discount WHERE idDiscount = "+id+";");
             result.next();
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
