package Model.product;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 * details: DAO for the table Discount in the database, manage the insertion / update / selection of different discount in the DB
 */
public class DiscountDAO {
       private Connection connection;  
    
    /**
     * Constructor
     */
    public DiscountDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Insert a discount in the database
     * @param value
     * @return boolean: result
     */
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
    
    /**
     * Insert a discount in the database and update the movie containing the discount
     * @param discount
     * @param movie
     * @return boolean: result
     */
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
    
    /**
     * Retrieve discount from the database
     * @param id
     * @return Discount: result
     */
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
