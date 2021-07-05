package Model.product;

import Model.dataAccessModule.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Alessio
 * @author Adam
 */
public class MovieDAO {
    private DBConnection connection;  
    
    public MovieDAO() {
        this.connection = new DBConnection();
    }
    
    public boolean addMovie(Movie movie) {
        PreparedStatement preparedStatement = null;
        boolean success = false;
        try {
            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO Movie (details, diffusion, ticketPrice, title, author, releaseDate, discountId, movieGenreId, quantityLeft) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, movie.getDetails());
            preparedStatement.setTimestamp(2, new Timestamp(movie.getDiffusion().getTime()));
            preparedStatement.setDouble(3, movie.getTicketPrice());
            preparedStatement.setString(4, movie.getTitle());
            preparedStatement.setString(5, movie.getAuthor());
            preparedStatement.setTimestamp(6, new Timestamp(movie.getRealeaseDate().getTime()));
            preparedStatement.setInt(7, movie.getDiscount().getId());
            preparedStatement.setInt(8, movie.getGenre().ordinal());
            preparedStatement.setInt(9, movie.getQuantityleft());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    
    public ArrayList<Movie> getMovies() {
        ResultSet result = null;
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Movie");
            while (result.next()) {      
                Discount discount = result.getInt("discountId") != 0 ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
                movies.add(new Movie(
                        result.getInt("idMovie"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDate("realeaseDate"),
                        discount,
                        result.getInt("quantityleft"),
                        result.getString("details"),
                        result.getDate("diffusion"),
                        result.getDouble("ticketPrice"),
                        MovieGenre.values()[result.getInt("movieGenreId")-1]
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public Movie getMovieById(int id) {
        ResultSet result = null;
        Movie movie = null;
        try {
            result = connection.getConnection().createStatement().executeQuery("SELECT * FROM Movie WHERE idMovie = "+id+";"); 
            Discount discount = result.getInt("discountId") != 0 ? new DiscountDAO().getDiscountById(result.getInt("discountId")) : null;
            movie= new Movie(
                    result.getInt("idMovie"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getDate("realeaseDate"),
                    discount,
                    result.getInt("quantityleft"),
                    result.getString("details"),
                    result.getDate("diffusion"),
                    result.getDouble("ticketPrice"),
                    MovieGenre.values()[result.getInt("movieGenreId")-1]
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }
    
}
